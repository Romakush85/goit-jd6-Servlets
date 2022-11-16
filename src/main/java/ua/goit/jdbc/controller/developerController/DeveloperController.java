package ua.goit.jdbc.controller;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.service.converter.DeveloperConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/developers")
public class DeveloperController extends HttpServlet {
    private DeveloperService developerService;
    private DeveloperRepository developerRepository;

    @Override
    public void init() throws ServletException {
        String dbUsername = System.getenv("dbUsername");
        String dbPassword = System.getenv("dbPassword");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        developerRepository = new DeveloperRepository(dbManager);
        DeveloperConverter developerConverter = new DeveloperConverter();
        developerService = new DeveloperService(developerRepository, developerConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer devId =Integer.parseInt(req.getParameter("devId"));
        Optional<DeveloperDto> optional = developerService.findById(devId);
        req.setAttribute("developer", optional.orElseGet(() -> {return null;}));
        req.getRequestDispatcher("/WEB-INF/jsp/developer/findDeveloper.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "create":
                create(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
        }    }

    protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String gender = req.getParameter("gender");
        Integer salary = Integer.parseInt(req.getParameter("salary"));
        DeveloperDto developer = new DeveloperDto();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setBirthDate(date);
        developer.setGender(gender);
        developer.setSalary(salary);
        developerService.save(developer);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/savedDeveloper.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DeveloperDto developer = new DeveloperDto();
        developer.setDevId(Integer.parseInt(req.getParameter("devId")));
        developer.setFirstName(req.getParameter("firstName"));
        developer.setLastName(req.getParameter("lastName"));
        developer.setBirthDate(date);
        developer.setGender(req.getParameter("gender"));
        developer.setSalary(Integer.parseInt(req.getParameter("salary")));
        if(developerService.update(developer)){
            req.setAttribute("result", "Developer updated!");
        } else {
            req.setAttribute("result", "Developer not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/developer/updateDeveloperForm.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer devId = Integer.parseInt(req.getParameter("devId"));
        if(developerService.findById(devId).isPresent()){
            developerRepository.deleteById(devId);
            req.setAttribute("result", "Developer  deleted!");
        } else {
            req.setAttribute("result", "Developer not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/developer/deleteDeveloperForm.jsp").forward(req, resp);
    }


}
