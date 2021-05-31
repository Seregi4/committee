package com.levanov.servlets;


import com.levanov.dao.FacultyDAO;
import com.levanov.dao.SpecialtyDAO;
import com.levanov.model.Faculty;
import com.levanov.model.Specialty;
import com.levanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userFromDB");

        if (user.getRoleID() == 1) {

            FacultyDAO facultyDAO = new FacultyDAO();
            List<Faculty> listFac = facultyDAO.getAllFaculty();

            request.setAttribute("list", listFac);
            request.setAttribute("cc", true);
            request.getRequestDispatcher("WEB-INF/JSP/adminPage.jsp").forward(request, response);

        } else request.setAttribute("errMessage", "login doesn't exist");
        request.getRequestDispatcher("WEB-INF/JSP/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FacultyDAO facultyDAO = new FacultyDAO();

        //request.setAttribute("list", listFac);

        int id;

//----------------------OPEN NEW-FACULTY PANEL-------------------------
        if (request.getParameter("new-faculty") != null) {

            request.setAttribute("new1", true);
//-----------------------BACK TO MAIN PAGE---------------------------------------
        } else if (request.getParameter("home") != null) {
            request.getRequestDispatcher("WEB-INF/JSP/index.jsp").forward(request, response);
//-----------------------OPEN FACULTY------------------------------------------
        } else if (request.getParameter("open-faculty") != null) {
            id = Integer.parseInt(request.getParameter("open-faculty"));
            Faculty fac = facultyDAO.getFacultyByID(id);
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            request.setAttribute("listspec", specialtyDAO.getSpecialtyByFacultyId(id));
            request.setAttribute("facult", true);
            request.setAttribute("fac", fac);

//--------------------------OPEN ADD-SPECIALTY PANEL-----------------------------------------------
        } else if (request.getParameter("add-speciality") != null) {
            id = Integer.parseInt(request.getParameter("add-speciality"));
            Faculty fac = facultyDAO.getFacultyByID(id);
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            request.setAttribute("listspec", specialtyDAO.getSpecialtyByFacultyId(id));
            request.setAttribute("fac", fac);
            request.setAttribute("add", true);

//-------------------------DELETE FACULTY---------------------------------------------------------
        } else if (request.getParameter("delete") != null) {
            id = Integer.parseInt(request.getParameter("delete"));
            facultyDAO.deletesFacultyById(id);

            request.setAttribute("cc", true);

//--------------------------OPEN EDIT-FACULTY PANEL------------------------------------------------------
        } else if (request.getParameter("edit") != null) {
            id = Integer.parseInt(request.getParameter("edit"));
            Faculty fac = facultyDAO.getFacultyByID(id);
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            request.setAttribute("listspec", specialtyDAO.getSpecialtyByFacultyId(id));
            request.setAttribute("edit", true);
            request.setAttribute("fac", fac);

//-----------------------------SAVE NEW FACULTY---------------------------------------------------------
        } else if (request.getParameter("new-name") != null && request.getParameter("new-description") != null) {

            String newName = request.getParameter("new-name");
            String newDescription = request.getParameter("new-description");

            Faculty faculty = new Faculty();
            faculty.setName(newName);
            faculty.setDescription(newDescription);

            facultyDAO.insertFaclulty(faculty);

            request.setAttribute("facult", true);
            request.setAttribute("fac", faculty);

//-------------------------SAVE EDIT FACULTY----------------------------------------------------------
        } else if (request.getParameter("edit-name") != null && request.getParameter("edit-description") != null) {
            id = Integer.parseInt(request.getParameter("editfac"));
            String editedName = request.getParameter("edit-name");
            String editedDescription = request.getParameter("edit-description");
            Faculty fac = facultyDAO.getFacultyByID(id);
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();

            fac.setName(editedName);
            fac.setDescription(editedDescription);

            facultyDAO.updateFaculty(fac);

            request.setAttribute("fac", fac);
            request.setAttribute("facult", true);
            request.setAttribute("listspec", specialtyDAO.getSpecialtyByFacultyId(id));
//--------------------------OPEN SPECIALTY-------------------------------------------------
        } else if (request.getParameter("id-spec") != null && request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            final int idSpec = Integer.parseInt(request.getParameter("id-spec"));
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            Faculty fac = facultyDAO.getFacultyByID(id);
            List<Specialty> specialtyList = new ArrayList<>(specialtyDAO.getSpecialtyByFacultyId(id));


            request.setAttribute("fac", fac);
            request.setAttribute("listspec", specialtyList);
            request.setAttribute("spec", specialtyList.stream().filter(spec -> spec.getId() == idSpec).findFirst().orElse(null));
            request.setAttribute("speciality", true);

//---------------------------SAVE NEW SPECIALTY---------------------------------------------------
        } else if (request.getParameter("spec-name") != null &&
                request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));

            String specName = request.getParameter("spec-name");

            Faculty fac = facultyDAO.getFacultyByID(id);

            //TODO default score 6
            Specialty specialty = new Specialty(specName, id);
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            specialtyDAO.insertSpecialty(specialty);
            request.setAttribute("listspec", specialtyDAO.getSpecialtyByFacultyId(id));
            request.setAttribute("fac", fac);
            request.setAttribute("speciality", true);
            request.setAttribute("spec", specialty);

//-----------------------------OPEN EDIT SPECIALTY PANEL----------------------------------------------------
        } else if (request.getParameter("edit-specialty") != null && request.getParameter("id-faculty") != null) {
            int idSpec = Integer.parseInt(request.getParameter("edit-specialty"));
            id = Integer.parseInt(request.getParameter("id-faculty"));
            Faculty fac = facultyDAO.getFacultyByID(id);
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            List<Specialty> specialtys = specialtyDAO.getSpecialtyByFacultyId(id);
            Specialty specialty = specialtys.stream().filter(spec -> spec.getId() == idSpec).findFirst().orElse(null);

            request.setAttribute("fac", fac);
            request.setAttribute("editspecialty", true);
            request.setAttribute("spec", specialty);
            request.setAttribute("listspec", specialtys);

//-----------------------------SAVE EDIT SPECIALTY-----------------------------------------------
        } else if (request.getParameter("edit-spec-name") != null) {
            System.out.println("enter");
            id = Integer.parseInt(request.getParameter("id"));
            System.out.println("id faculty" + id);
            int idSpec = Integer.parseInt(request.getParameter("id-spec"));
            System.out.println("idspec" + idSpec);
            String editName = request.getParameter("edit-spec-name");
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();

            Faculty fac = facultyDAO.getFacultyByID(id);
            List<Specialty> specialtyList = specialtyDAO.getSpecialtyByFacultyId(id);

            Specialty specialty = specialtyList.stream().filter(spec -> spec.getId() == idSpec).findFirst().orElse(null);

            specialty.setName(editName);

            specialtyDAO.updateSpecialty(specialty);
            System.out.println(specialty.getName());
            request.setAttribute("listspec", specialtyDAO.getSpecialtyByFacultyId(id));
            request.setAttribute("fac", fac);
            request.setAttribute("spec", specialty);
            request.setAttribute("speciality", true);

//---------------------------DELETE SPECIALTY----------------------------------------------------
        } else if (request.getParameter("delete-specialty") != null && request.getParameter("id-faculty") != null) {
            id = Integer.parseInt(request.getParameter("id-faculty"));

            int idSpec = Integer.parseInt(request.getParameter("delete-specialty"));
            SpecialtyDAO specialtyDAO = new SpecialtyDAO();
            Faculty fac = facultyDAO.getFacultyByID(id);

            specialtyDAO.deletesSpecialtyById(idSpec);
            ;
            request.setAttribute("listspec", specialtyDAO.getSpecialtyByFacultyId(id));
            request.setAttribute("fac", fac);
            request.setAttribute("facult", true);

//-----------------------------------DEFAULT----------------------------------------------------------
        } else {
            request.setAttribute("cc", true);
        }
        List<Faculty> listFac = facultyDAO.getAllFaculty();
//-----------Execution for everyone:
        request.setAttribute("list", listFac);
        request.getRequestDispatcher("WEB-INF/JSP/adminPage.jsp").forward(request, response);
    }
}