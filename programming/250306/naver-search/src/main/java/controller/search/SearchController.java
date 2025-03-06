package controller.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.NaverAPIResultItem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.search.SearchService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchKeyword = req.getParameter("keyword");
        String json;
        try {
            SearchService searchService = new SearchService();
            List<NaverAPIResultItem> result = searchService.searchByKeyword(searchKeyword);
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            resp.sendError(500);
            json = """
                    {
                    "error": "%s"
                    }
                    """.formatted(e.getMessage());
        }

        // 응답 설정
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // JSON 응답 전송
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}