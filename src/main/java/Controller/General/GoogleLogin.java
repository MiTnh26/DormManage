package Controller.General;

import Utils.AppConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/google-login")
public class GoogleLogin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String googleLoginUrl = "https://accounts.google.com/o/oauth2/auth?"
                + "scope=openid%20email%20profile&"
                + "redirect_uri=" + AppConfig.getProperty("gg.uri") + "&"
                + "response_type=code&"
                + "approval_prompt=force&"
                + "client_id=" + AppConfig.getProperty("gg.id");


        response.sendRedirect(googleLoginUrl);
    }

}
