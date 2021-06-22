import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

class Security {

    int marks;
    int fLog = 0, fReg = 0, faLog = 0;
    String ID, Pass, cPass;
    private String Id;
    static int choice, Status;
    static Scanner scan = new Scanner(System.in);

    public void adminLogin() {

        while (faLog == 0) {
            String pass = null;
            scan.nextLine();
            System.out.println();
            System.out.print("  ID: ");
            ID = scan.nextLine();
            System.out.print("  Password: ");
            Pass = scan.nextLine();

            try {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    System.out.println("Loading Driver failed");
                }

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaustubh", "root",
                        "");
                String sql = "SELECT * FROM admin";
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Id = rs.getString(2);
                    pass = rs.getString(3);
                }
                if (Pass.contentEquals(pass) && (ID.contentEquals(Id))) {
                    System.out.println("\n  Login Succesfully");
                    faLog = 1;
                } else
                    System.out.println("\n  Incorrect ID or Password");

            } catch (Exception ae) {
                System.out.println("\n  Id not exits");
            }

        }
    }

    public void changeAdmin() {

        int i = 0;
        while (i == 0) {
            System.out.print("  ID: ");
            ID = scan.nextLine();
            System.out.print("  Password: ");
            Pass = scan.nextLine();
            try {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    System.out.println("Loading Driver failed");

                }
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaustubh", "root",
                        "");
                String sql = "update admin set Name=?,Password=? Where ID=1";
                PreparedStatement st1 = con.prepareStatement(sql);
                st1.setString(1, ID);
                st1.setString(2, Pass);
                st1.executeUpdate();
                System.out.println("\n  Admin credentials updated successfully");
                i = 1;
            } catch (Exception e) {
            }
            ;
        }

    }

    public void stdReg() {

        while (fReg == 0) {
            scan.nextLine();
            System.out.print("  ID: ");
            ID = scan.nextLine();
            System.out.print("  Password: ");
            Pass = scan.nextLine();
            System.out.print("  Confirm Password: ");
            cPass = scan.nextLine();

            if (Pass.equals(cPass)) {

                try {
                    try {

                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e1) {
                        System.out.println("Loading Driver failed");
                    }

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaustubh", "root",
                            "");

                    String sql = "insert into user(ID,Password) values(?,?)";
                    PreparedStatement st1 = con.prepareStatement(sql);
                    st1.setString(1, ID);
                    st1.setString(2, Pass);
                    st1.executeUpdate();
                    System.out.println("\n  Congrats! You have successfully registered");
                    System.out.println("  Please login again !");
                    fReg = 1;
                    break;

                } catch (Exception ae) {
                    System.out.println("\n  Inserted info already exits in database");
                }

            } else
                System.out.println("\n  Passwords doesn't match !");
        }
    }

    public void stdLogin() {

        while (fLog == 0) {
            String pass = null;
            scan.nextLine();
            System.out.print("  ID: ");
            ID = scan.nextLine();
            System.out.print("  Password: ");
            Pass = scan.nextLine();

            try {
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    System.out.println("Loading Driver failed");
                }

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam", "root",
                        "");
                String sql = "SELECT * FROM user Where ID='" + ID + "'";
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Id = rs.getString(1);
                    pass = rs.getString(2);
                    Status = rs.getInt(4);
                }
                if (Pass.contentEquals(pass) && (ID.contentEquals(Id))) {
                    System.out.println("\n  Login Succesfully\n");
                    fLog = 1;
                } else
                    System.out.println("\n  Incorrect ID or Password\n");

            } catch (Exception ae) {
                System.out.println("\n  Id not exits");
            }

        }

    }

    public void authentication() {

        Exam s2 = new Exam();

        System.out.print("\n  1.Student Login\n  2.Student Registration\n  3.Back\n  Choose Option: ");
        choice = scan.nextInt();
        System.out.println();
        switch (choice) {

        case 1:
            stdLogin();
            s2.Main();
            break;
        case 2:
            stdReg();
            System.out.println();
            break;
        case 3:
            Exam.main(null);
            break;
        }

    }
}

public class Exam extends Security {

    int gOp;
    int Id;
    String IDN;
    int flag2 = 0, flgP = 0;
    int flag = 0;
    String que[] = new String[20];
    String op1[] = new String[20];
    String op2[] = new String[20];
    String op3[] = new String[20];
    String op4[] = new String[20];
    int cOp[] = new int[20];
    int id[] = new int[20];
    static Scanner scan = new Scanner(System.in);
    static int Qno = 5;
    static int status = 1;
    static int rstFlg = 0;
    static int rstFlg2 = 0;
    static int inM = 1;

    public void Main() {

        while (true) {
            System.out.print("\n  1.Exam mode\n  2.Practice Mode\n  3.Back\n  Choose Option : ");
            choice = scan.nextInt();

            switch (choice) {

            case 1:
                String c2 = "y";
                scan.nextLine();
                if (Status == 0) {
                    System.out.print("\n  Are you sure to start online exam ? [y/n] : ");
                    String c = scan.nextLine();
                    System.out.println();
                    if (c.equals(c2))
                        load();
                    System.out.println("  Online exam successfully finished.\n  Best of Luck For Results");
                    Status = 1;
                } else
                    System.out.println("\n  Sorry! You are not able for online exam now.");
                break;

            case 2:
                flgP = 1;
                load();
                System.out.println("   Examination Finished\n  Best of Luck For Results");
            case 3:
                authentication();
                break;

            default:
                System.out.println("\n  Invalid Option");

            }
        }
    }

    public void adminPanel() {

        while (true) {

            System.out.print(
                    "\n  1.Edit Admin info\n  2.Set Question Paper\n  3.Set number of Questions on paper\n  4.Set marks to question\n  5.View Question Paper\n  6.Delete Question from Paper\n  7.View Results\n  8.Reset\n  9.Back\n  Choose Option : ");
            choice = scan.nextInt();
            System.out.println();

            switch (choice) {

            case 1:
                changeAdmin();
                break;
            case 2:
                setQP();
                break;

            case 3:
                setQno();
                break;

            case 4:
                System.out.print("\n  Enter Marks for correct answer of each question:");
                inM = scan.nextInt();
                System.out.println("\n  Data updated successfully");
                break;

            case 5:
                flag = 1;
                load();
                break;

            case 6:
                delQ();
                break;

            case 7:
                viewQP();
                break;
            case 8:
                System.out.print(
                        "  Making Students Eligible for Exam\n\n  1.Reset All (Make all students eligible for online exam)\n  2.Reset By ID (Make specific student eligible for online exam)\n  3.Back\n  Choose Option:");
                choice = scan.nextInt();
                switch (choice) {

                case 1:
                    rstFlg = 1;
                    submit();
                    System.out.println("\n  Data Reseted Successfully");
                    break;

                case 2:
                    rstFlg2 = 1;
                    rstFlg = 1;
                    scan.nextLine();
                    System.out.print("\n  Enter ID to reset :");
                    IDN = scan.nextLine();
                    System.out.println();
                    submit();
                    System.out.println("\n  Data Reseted Successfully");
                    break;

                case 3:
                    break;

                default:
                    System.out.println("\n  Invalid option");
                }
                break;

            case 9:
                Exam.main(null);
                break;

            default:
                System.out.println("\n  Invalid Option\n");
            }
        }
    }

    public void setQP() {

        System.out.println("\n\t\tSet the Question Paper");
        scan.nextLine();
        System.out.print("  Que no : ");
        Id = scan.nextInt();
        System.out.print("\n  Question : ");
        scan.nextLine();
        que[0] = scan.nextLine();
        System.out.print("\n  Option 1 : ");
        op1[0] = scan.nextLine();
        System.out.print("\n  Option 2 : ");
        op2[0] = scan.nextLine();
        System.out.print("\n  Option 3 : ");
        op3[0] = scan.nextLine();
        System.out.print("\n  Option 4 : ");
        op4[0] = scan.nextLine();
        System.out.print("\n  Correct option : ");
        cOp[0] = scan.nextInt();

        try {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
                System.out.println("Loading Driver failed");
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam", "root",
                    "");
            String sql = "insert into exam(ID,que,op1,op2,op3,op4,cop) values(?,?,?,?,?,?,?)";
            PreparedStatement st1 = con.prepareStatement(sql);
            st1.setInt(1, Id);
            st1.setString(2, que[0]);
            st1.setString(3, op1[0]);
            st1.setString(4, op2[0]);
            st1.setString(5, op3[0]);
            st1.setString(6, op4[0]);
            st1.setInt(7, cOp[0]);
            st1.executeUpdate();
            System.out.println("\n  Question added successfully");

        } catch (Exception ae) {
        }
    }

    public void setQno() {

        System.out.print("  Enter how many question you want to add in the online exam? (Default 5)");
        Qno = scan.nextInt();
        System.out.println("  Data updated successfully");
    }

    public void delQ() {
        try {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
                System.out.println("Loading Driver failed");
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam", "root",
                    "");
            String sql = "delete from exam Where ID=?";
            PreparedStatement st1 = con.prepareStatement(sql);
            System.out.print("\n  Enter Que no. which you want to delete : ");
            int ID1 = scan.nextInt();
            st1.setInt(1, ID1);
            st1.executeUpdate();
            System.out.println("\n  Question deleted successfully\n");

        } catch (Exception ae) {
        }
    }

    public void viewQP() {
        try {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            ;

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam", "root",
                    "");
            String sql = "SELECT * FROM user";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("  ID            Marks");
            while (rs.next()) {
                String id = rs.getString(1);
                int m = rs.getInt(3);
                System.out.println("  " + id + "         " + m);
            }
        } catch (Exception ae) {
        }
        ;
    }

    public void load() {

        int i = 0;

        try {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
            }
            Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam", "root",
                    "");
            String query;

            if (flag2 == 1) {
                query = "SELECT * FROM exam";
            } else {
                query = "SELECT * FROM exam";
            }
            PreparedStatement st = Conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Id++;
                id[i] = rs.getInt(1);
                que[i] = rs.getString(2);
                op1[i] = rs.getString(3);
                op2[i] = rs.getString(4);
                op3[i] = rs.getString(5);
                op4[i] = rs.getString(6);
                cOp[i] = rs.getInt(7);

                i++;
            }

            check();

        } catch (Exception e) {

        }
        ;

    }

    public void check() {

        char ID2 = 65;

        if (flgP == 0 && flag == 0) {
            System.out.print("  ID: ");
            IDN = scan.nextLine();
            System.out.println("\n  Examination Started Successfully");
        }
        System.out.println();

        // To avoid repetition of random number
        Random rm = new Random();
        int[] rn = new int[20];
        for (int x = 0; x < Qno; x++) {
            rn[x] = rm.nextInt(Id - 1) + 1;
            if (x > 0)
                for (int c = 0; c <= x - 1; c++)
                    if (rn[c] == rn[x])
                        x--;
        }

        if (flag == 1) {
            for (int i = 0; i < Id; i++) {

                System.out.println("  " + id[i] + ") " + que[i]);
                System.out.println("  Option 1 : " + op1[i]);
                System.out.println("  Option 2 : " + op2[i]);
                System.out.println("  Option 3 : " + op3[i]);
                System.out.println("  Option 4 : " + op4[i]);
                System.out.println("  Ans : " + cOp[i]);
                System.out.println();

            }
        } else {

            for (int i = 0; i < Qno; i++) {
                System.out.println("  " + ID2 + ") " + que[rn[i]]);
                System.out.println("  1) " + op1[rn[i]]);
                System.out.println("  2) " + op2[rn[i]]);
                System.out.println("  3) " + op3[rn[i]]);
                System.out.println("  4) " + op4[rn[i]]);
                System.out.print("  Ans : ");
                gOp = scan.nextInt();
                System.out.println();
                if (gOp == cOp[rn[i]]) {
                    if (flgP == 0) {
                        submit();
                    } else {
                        System.out.println("  Correct Answer\n");
                    }
                } else if (flgP == 1) {
                    System.out.print("  Wrong !");
                    System.out.println("  Ans = " + cOp[rn[i]]);
                    System.out.println();
                }
                ID2++;
            }

        }
        Id = 0;
        flag = 0;
        flgP = 0;
    }

    public void submit() {
        try {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
                System.out.println("Loading Driver failed");

            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaustubh", "root",
                    "");

            if (rstFlg == 0) {
                String sql = "update user set Marks=?,Status=? Where ID=?";
                PreparedStatement st1 = con.prepareStatement(sql);
                marks += inM;
                st1.setInt(1, marks);
                st1.setInt(2, status);
                st1.setString(3, IDN);
                st1.executeUpdate();
            } else {
                String sql;
                if (rstFlg2 == 1) {
                    sql = "update user set Status=?,Marks=? Where ID=?";
                } else {
                    sql = "update user set Status=?, Marks=?";
                }

                PreparedStatement st1 = con.prepareStatement(sql);
                st1.setInt(1, 0);
                st1.setInt(2, 0);
                if (rstFlg2 == 1) {
                    st1.setInt(2, 0);
                    st1.setString(3, IDN);
                }
                st1.executeUpdate();            }
            rstFlg2 = 0;
            rstFlg = 0;
        } catch (Exception ae) {
            System.out.println(ae);
        }
        ;
    }

    public static void main(String[] args) {

        Security obj = new Security();
        Exam s = new Exam();

        while (true) {
System.out.println("\n\n  \tOnline Examination System\n");
            System.out.println("\n******************************************************************************\n");
            System.out.print("\n  1.Admin Login\n  2.Student Options\n  3.About\n\n  Choose Option: ");
            choice = scan.nextInt();

            switch (choice) {

            case 1:
                s.adminLogin();
                s.adminPanel();
                break;
            case 2:
                obj.authentication();
                break;

    
            case 3:
                System.out.println("\n****************************************************************************\n");
                System.out.println("\n\n\n\n  \t\tOnline Examination System \n\n\t\t\t(2020-2021)");
                System.out.println("\n  Guided by -");
                System.out.println("\n  Dr.P.R.Satav Sir");
                System.out.println("\n***************************************************************************");
                System.out.println("\n  Developed by -");
                System.out.println("\n  Batch : CM1");
                System.out.println("\n  Kaustubh Bhule (18CM009)");
                System.out.println("\n  Purval Dhumale (18CM015)");
                System.out.println("\n  Jaydeep Gedam (18CM017)");
                System.out.println("\n  Vaishnav Ghenge (18CM019)");
                System.out.println("\n  Parth Bamnote (18CM004)");
                System.out.println("\n  Shantanu Barad (18CM006)");
                System.out.println("\n****************************************************************************");
                System.out.println("\n  MicroPhone Used -");
                System.out.println("\n  Realtek(R) Audio");
                System.out.println("\n****************************************************************************");
                System.out.println("\n  \tThank you");
                System.out.println("\n  Press \"Enter\" to exit ");
                scan.nextLine();
                scan.nextLine();
                System.out.println("\n****************************************************************************");
                break;
                
            default:
                System.out.println("  Invalid Option\n");
            }
        }
    }
}
