import java.sql.*;
import java.util.*;

public class Exam extends Security {

    int marks = 0;

    Connection con = Security.con;
    PreparedStatement st = Security.st;
    ResultSet rs = Security.rs;

    static Scanner scan = new Scanner(System.in);

    public void banner() {
        clear();
        System.out.print("------------------------------------------\n");
        System.out.print("\tOnline Examination System");
        System.out.print("\n------------------------------------------\n");
    }

    public void startExam(String sid) {
        new Exam();
        try {
            sql = "SELECT * FROM user";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getInt(4) == 1) {
                    System.out.println("\n  You are not eligible for exam...");
                    pause("back");
                    main(null);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\n  Something Went Wrong...");
        }
        scan.nextLine();
        System.out.print("\n  Are you sure to start online exam ? [y/n]: ");
        if ((scan.nextLine()).equals("y")) {
            try {
                banner();
                System.out.println("\n  Exam Started - ");
                sql = "SELECT * FROM exam";
                st = con.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    System.out.println("\n  " + rs.getInt(1) + ") " + rs.getString(2));
                    System.out.println("  Option 1: " + rs.getString(3));
                    System.out.println("  Option 2: " + rs.getString(4));
                    System.out.println("  Option 3: " + rs.getString(5));
                    System.out.println("  Option 4: " + rs.getString(6));
                    System.out.print("\n  Correct option: ");
                    if (scan.nextInt() == rs.getInt(7)) {
                        marks += rs.getInt(8);
                    }
                }
                sql = "UPDATE user SET marks=?, status=? WHERE id=?";
                st = con.prepareStatement(sql);
                st.setInt(1, marks);
                st.setInt(2, 1);
                st.setString(3, sid);
                st.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("\n  Something Went Wrong...");
            }
            System.out.println("\n  Online exam successfully finished...\n\n  Best of Luck For Results...");
            pause("back");
            main(null);
        } else {
            Exam.main(null);
        }
    }

    public void adminPanel() {

        new Exam();

        while (true) {

            banner();
            System.out.print(
                    "\n  Admin Panel - \n\n  1] Set Question Paper\n  2] View Question Paper\n  3] Delete Question from Paper\n  4] View Results\n  5] Reset\n  6] Back\n\n  Choose Option: ");
            choice = scan.nextInt();
            System.out.println();

            switch (choice) {

                case 1:
                    setQp();
                    break;

                case 2:
                    viewQp();
                    break;

                case 3:
                    delQ();
                    break;

                case 4:
                    viewRs();
                    break;

                case 5:
                    reset();
                    break;

                case 6:
                    Exam.main(null);
                    break;

                default:
                    System.out.println("\n  Invalid Option...\n");
                    pause("back");
            }
        }

    }

    public void setQp() {
        banner();
        System.out.println("\n  Set the Question Paper - ");
        System.out.print("\n  Enter total Ques to add: ");
        int tque = scan.nextInt();
        try {
            for (int i = 0; i < tque; i++) {
                sql = "INSERT INTO exam(id,que,op1,op2,op3,op4,cop,marks) VALUES(?,?,?,?,?,?,?,?)";
                st = con.prepareStatement(sql);
                System.out.print("\n  Que no: ");
                st.setInt(1, scan.nextInt());
                scan.nextLine();
                System.out.print("\n  Question: ");
                st.setString(2, scan.nextLine());
                System.out.print("\n  Option 1: ");
                st.setString(3, scan.nextLine());
                System.out.print("\n  Option 2: ");
                st.setString(4, scan.nextLine());
                System.out.print("\n  Option 3: ");
                st.setString(5, scan.nextLine());
                System.out.print("\n  Option 4: ");
                st.setString(6, scan.nextLine());
                System.out.print("\n  Correct Option: ");
                st.setInt(7, scan.nextInt());
                System.out.print("\n  Marks: ");
                st.setInt(8, scan.nextInt());
                st.executeUpdate();
            }
            System.out.println("\n  Questions added successfully...");
            pause("back");
        } catch (Exception e) {
            System.out.println("\n  Something Went Wrong...");
        }
    }

    public void viewQp() {
        banner();
        System.out.println("\n  View Question Paper - ");
        try {
            sql = "SELECT * FROM exam";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("\n  " + rs.getInt(1) + ") " + rs.getString(2));
                System.out.println("  Option 1: " + rs.getString(3));
                System.out.println("  Option 2: " + rs.getString(4));
                System.out.println("  Option 3: " + rs.getString(5));
                System.out.println("  Option 4: " + rs.getString(6));
                System.out.println("  Correct Option: " + rs.getInt(7));
                System.out.println("  Marks: " + rs.getInt(8));
            }
            pause("back");
        } catch (Exception e) {
            System.out.println("\n  Something Went Wrong...");
        }
    }

    public void delQ() {
        banner();
        System.out.println("\n  Delete Question from Paper - ");
        try {
            sql = "DELETE FROM exam WHERE id=?";
            st = con.prepareStatement(sql);
            System.out.print("\n  Enter Que no. which you want to delete: ");
            st.setInt(1, scan.nextInt());
            st.executeUpdate();
            System.out.println("\n  Question deleted successfully....\n");
            pause("back");
        } catch (Exception e) {
            System.out.println("\n  Something Went Wrong...");
        }
    }

    public void viewRs() {
        banner();
        System.out.println("\n  View Results - ");
        try {
            sql = "SELECT * FROM user";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            System.out.println("\n  ID\t\tMarks\n");
            while (rs.next()) {
                if (rs.getInt(3) >= 0) {
                    System.out.println("  " + rs.getString(1) + "\t" + rs.getInt(3));
                }
            }
            pause("back");
        } catch (Exception e) {
            System.out.println("\n  Something Went Wrong...");
        }
    }

    public void reset() {
        banner();
        System.out.print("\n  Reset - \n\n  1] Reset All (Make all students eligible for online exam)\n  2] Reset By ID (Make specific student eligible for online exam)\n  3] Back\n\n  Choose Option: ");
        choice = scan.nextInt();
        switch (choice) {

            case 1:
                try {
                    sql = "UPDATE user SET marks=?, status=?";
                    st = con.prepareStatement(sql);
                    st.setInt(1, -1);
                    st.setInt(2, 0);
                    st.executeUpdate();
                    System.out.println("\n  Data Reseted Successfully...");
                    pause("back");
                } catch (Exception e) {
                    System.out.println("\n  Something Went Wrong...");
                }
                break;

            case 2:
                try {
                    System.out.print("\n  Enter ID to reset: ");
                    String sid = scan.next();
                    sql = "UPDATE user SET marks=?, status=? WHERE id=?";
                    st = con.prepareStatement(sql);
                    st.setInt(1, -1);
                    st.setInt(2, 0);
                    st.setString(3, sid);
                    st.executeUpdate();
                    System.out.println("\n  Data Reseted Successfully...");
                    pause("back");
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("\n  Something Went Wrong...");
                }
                break;

            default:
                System.out.println("\n  Invalid option...");
                pause("back");
        }
    }

    public void pause(String context) {
        System.out.print("\n  Press ENTER to " + context + "...");
        scan.nextLine();
        scan.nextLine();
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        Security sec = new Security();
        Exam ex = new Exam();

        while (true) {
            ex.banner();
            System.out.print("\n  Main Menu -\n\n  1] Admin\n  2] Student\n  3] Exit\n\n  Choose Option: ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    ex.adminLogin();
                    break;
                case 2:
                    sec.studOp();
                    break;
                case 3:
                    System.out.println("\n  Good Bye!\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n  Invalid option...");
                    ex.pause("back");
            }
        }
    }
}

class Security {

    String sid, csid, id, cid, pass, cpass, sql;
    static int choice, marks, status;
    public static Connection con;
    public static PreparedStatement st;
    public static ResultSet rs;

    Scanner scan = new Scanner(System.in);

    Security() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam_db", "root", "");
        } catch (Exception e) {
            System.out.println("Loading driver failed...");
        }
    }

    public void adminLogin() {

        Exam ex = new Exam();

        try {
            sql = "SELECT * FROM admin";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                cid = rs.getString(1);
                cpass = rs.getString(2);
            }
            while (status == 0) {
                ex.banner();
                String pass = null;
                System.out.print("\n  ID: ");
                id = scan.nextLine();
                System.out.print("\n  Password: ");
                pass = scan.nextLine();
                if (id.contentEquals(cid) && pass.contentEquals(cpass)) {
                    ex.adminPanel();
                } else {
                    System.out.println("\n  Incorrect Username or Password!");
                    ex.pause("retry");
                }
            }
        } catch (Exception e) {
            System.out.println("\n  Something Went Wrong...");
        }
    }

    public boolean studLogin() {
        Exam ex = new Exam();
        ex.banner();
        System.out.print("\n  ID: ");
        sid = scan.nextLine();
        try {
            sql = "SELECT * FROM user WHERE id=?";
            st = con.prepareStatement(sql);
            st.setString(1, sid);
            rs = st.executeQuery();
            while (rs.next()) {
                csid = rs.getString(1);
                cpass = rs.getString(2);
            }

            if (csid != null) {
                if (sid.contentEquals(csid)) {
                    System.out.print("\n  Password: ");
                    pass = scan.nextLine();
                    if (pass.contentEquals(cpass)) {
                        return true;
                    }
                }
            } else {
                System.out.print("\n  Password: ");
                pass = scan.nextLine();
                System.out.print("\n  Confirm Password: ");
                cpass = scan.nextLine();
                if (pass.contentEquals(cpass)) {
                    sql = "INSERT INTO user(id,pass) VALUES(?,?)";
                    st = con.prepareStatement(sql);
                    st.setString(1, sid);
                    st.setString(2, pass);
                    st.executeUpdate();
                    return true;
                } else {
                    System.out.println("\n  Passwords doesn't mached!");
                    ex.pause("retry");
                }
            }
            
        } catch (Exception e) {
            System.out.println("\n  Something Went Wrong...");
        }
        return false;
    }

    public void studOp() {
        Exam ex = new Exam();
        if (studLogin()) {
            ex.banner();
            System.out.print("\n  Welcome, " + sid + "\n\n  1] Start Exam\n  2] Back\n\n  Choose Option: ");
            choice = scan.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    ex.startExam(sid);
                    break;
                case 2:
                    Exam.main(null);
                    break;
                default:
                    System.out.println("\n  Invalid option...");
                    ex.pause("back");
            }
        } else {
            System.out.println("\n  Incorrect ID or Password!");
            ex.pause("retry");
            studLogin();
        }

    }
}
