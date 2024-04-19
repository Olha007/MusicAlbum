package ua.edu.znu.musicalbum.controller.filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import ua.edu.znu.musicalbum.controller.listener.ThymeleafConfigurationListener;

import java.io.IOException;
import java.util.Enumeration;

@WebFilter(urlPatterns = {"/LoginServlet", "/HomeServlet",
        "/GenresServlet", "/GenreAddServlet", "/GenreEditServlet",
        "/ArtistsServlet", "/ArtistAddServlet", "/ArtistEditServlet",
        "/GroupsServlet", "/GroupAddServlet", "/GroupEditServlet",
        "/SongsServlet", "/SongAddServlet", "/SongEditServlet",
        "/AlbumAssignmentServlet"},

        dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class ThymeleafFilter implements Filter {

    private TemplateEngine templateEngine;
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.templateEngine = (TemplateEngine) context.getAttribute(ThymeleafConfigurationListener.TEMPLATE_ENGINE_ATR);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        WebContext context = getWebContext(req, resp);

        // Встановити атрибути запиту як змінні в контексті Thymeleaf
        Enumeration<String> params = req.getAttributeNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            context.setVariable(param, req.getAttribute(param));
        }

        String nextUrl = (String) req.getAttribute("nextUrl");
        templateEngine.process(nextUrl, context, response.getWriter());
    }

    @Override
    public void destroy() {
    }

    private WebContext getWebContext(HttpServletRequest request, HttpServletResponse response) {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(context)
                .buildExchange(request, response);
        return new WebContext(webExchange);
    }
}

