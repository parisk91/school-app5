package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.controllerview.InsertForm;
import gr.aueb.cf.schoolapp.controllerview.SearchForm;
import gr.aueb.cf.schoolapp.controllerview.UpdateDeleteForm;

import java.awt.*;

public class Main {
    private static Menu menu;
    private static InsertForm insertForm;
    private static UpdateDeleteForm updateDeleteForm;
    //    private static UpdateDeleteForm2 updateDeleteForm2;
    private static SearchForm searchForm;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menu = new Menu();
//                    menu.setLocationRelativeTo(null);
//                    menu.setVisible(true);

                    searchForm = new SearchForm();
                    searchForm.setLocationRelativeTo(null);
                    searchForm.setVisible(false);

                    insertForm = new InsertForm();
                    insertForm.setLocationRelativeTo(null);
                    insertForm.setVisible(false);

                    updateDeleteForm = new UpdateDeleteForm();
                    updateDeleteForm.setLocationRelativeTo(null);
                    updateDeleteForm.setVisible(false);

//                    loginPage = new LoginPage();
//                    loginPage.setLocationRelativeTo(null);
//                    loginPage.setVisible(true);
//
//                    searchUsers = new SearchUsers();
//                    searchUsers.setLocationRelativeTo(null);
//                    searchUsers.setVisible(false);
//
//                    insertUsers = new InsertUsers();
//                    insertUsers.setLocationRelativeTo(null);
//                    insertUsers.setVisible(false);

//					version = new Version();
//					version.setLocationRelativeTo(null);
//					version.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static Menu getMenu() {
        return menu;
    }
    //
    public static SearchForm getSearchForm() {
        return searchForm;
    }

    public static InsertForm getInsertForm() {
        return insertForm;
    }

    public static UpdateDeleteForm getUpdateDeleteForm() {
        return updateDeleteForm;
    }

//    public static SearchUsers getSearchUsers() {
//        return searchUsers;
//    }
//
//    public static LoginPage getLoginPage() {
//        return loginPage;
//    }
//
//    public static InsertUsers getInsertUsers() {
//        return insertUsers;
//    }
}