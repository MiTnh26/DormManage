package Filter;

import Entity.Student;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/student/*")
public class StudentFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        if(Objects.isNull(student)){
            request.getRequestDispatcher("/views/error.jsp").forward(req, res);
            return;
        }
        super.doFilter(req, res, chain);
    }
}
