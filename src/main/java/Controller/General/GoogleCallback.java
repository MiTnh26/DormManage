package Controller.General;

import Entity.News;
import Entity.Student;
import Service.Impl.NewsServiceImpl;
import Service.Impl.StudentServiceImpl;
import Service.NewsService;
import Service.StudentService;
import Utils.AppConfig;
import com.google.api.client.util.Strings;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@WebServlet("/google-callback")
public class GoogleCallback extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();
    private final static String END_POINT = "fpt.edu.vn";
    private final static String ACCESS_DENIED = "access_denied";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (ACCESS_DENIED.equals(request.getParameter("error"))) {
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
            return;
        }
        String accessToken = getToken(code);
        JSONObject user = getUserInformation(accessToken);
        String email = user.getString("email");
        if (!Strings.isNullOrEmpty(email) && !email.endsWith(END_POINT)) {
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
        } else {
            Student student = studentService.getByGmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("student", student);
            response.sendRedirect(request.getContextPath() + "/student/home");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private String getToken(String code) throws IOException {
        // call api to get token

        String params = "code=" + code +
                "&client_id=" + AppConfig.getProperty("gg.id") +
                "&client_secret=" + AppConfig.getProperty("gg.secret") +
                "&redirect_uri=" + AppConfig.getProperty("gg.uri") +
                "&grant_type=authorization_code";

        URL url = new URL(AppConfig.getProperty("gg.token"));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes());
        conn.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        JSONObject jsonResponse = new JSONObject(response.toString());
        String accessToken = jsonResponse.getString("access_token");
        conn.disconnect();
        return accessToken;
    }


    private static JSONObject getUserInformation(String accessToken) throws IOException {

        URL urlUserInfo = new URL(AppConfig.getProperty("gg.user"));
        HttpURLConnection connUserInfo = (HttpURLConnection) urlUserInfo.openConnection();
        connUserInfo.setRequestProperty("Authorization", "Bearer " + accessToken);
        connUserInfo.connect();
        BufferedReader readerUserInfo = new BufferedReader(new InputStreamReader(connUserInfo.getInputStream()));
        StringBuilder userInfoResponse = new StringBuilder();
        String lineUserInfo;
        while ((lineUserInfo = readerUserInfo.readLine()) != null) {
            userInfoResponse.append(lineUserInfo);
        }
        readerUserInfo.close();

        JSONObject jsonUserInfo = new JSONObject(userInfoResponse.toString());
        connUserInfo.disconnect();
        return jsonUserInfo;
    }

}
