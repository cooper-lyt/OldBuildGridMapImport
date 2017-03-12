package com.wxy.House.HouseOwnerRecord;

import com.cooper.house.Q;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by wxy on 2017-03-12.
 */
public class LockedHouse {
    private static final String OUT_PATH_FILE = "/LockedHouse.sql";

    private static final String DB_HOUSE_URL = "jdbc:jtds:sqlserver://192.168.3.200:1433/DGHouseInfo";

    private static final String DB_HOUSE_OWNER_RECORD_URL="jdbc:mysql://127.0.0.1:3306/HOUSE_OWNER_RECORD";

    private static Connection houseConnection;

    private static Connection ownerRecordConnection;


    private static BufferedWriter sqlWriter;

    private static File recordFile;


    private static Statement statementHouse;

    private static Statement statementOwnerRecord;

    public static void main(String agr[]){

        recordFile = new File(OUT_PATH_FILE);
        if (recordFile.exists()){
            recordFile.delete();
        }

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            houseConnection = DriverManager.getConnection(DB_HOUSE_URL, "sa", "dgsoft");
            statementHouse = houseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //statementHousech = houseConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("houseConnection successful");
        } catch (Exception e) {
            System.out.println("houseConnection is errer");
            e.printStackTrace();
            return;
        }

        try {
            recordFile.createNewFile();
            FileWriter fw = new FileWriter(recordFile.getAbsoluteFile());
            sqlWriter = new BufferedWriter(fw);
            sqlWriter.write("USE HOUSE_OWNER_RECORD;");
            sqlWriter.newLine();

        } catch (IOException e) {
            System.out.println("sql 文件创建失败");
            e.printStackTrace();
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //ownerRecordConnection = DriverManager.getConnection(DB_HOUSE_OWNER_RECORD_URL, "root", "isNull");
            ownerRecordConnection = DriverManager.getConnection(DB_HOUSE_OWNER_RECORD_URL, "root", "dgsoft");
            statementOwnerRecord = ownerRecordConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("ownerRecordConnection successful");
        } catch (Exception e) {
            System.out.println("ownerRecordConnection is errer");
            e.printStackTrace();
            return;
        }


        try {

            //房屋状态为在建工程抵押，查封，不可售，异议，灭籍,声明作废,房屋已注销(灭籍) 导入预警 抵押(做完初始登记直接做的抵押)
            ResultSet setd = statementHouse.executeQuery("select h.*,b.no as bno from DGHouseInfo..house as h left join DGHouseInfo..Build as b on h.buildid=b.id" +
                    " where houseState=890 or houseState=99 or houseState=127 or houseState=116 or houseState=115 or houseState=117 or houseState=119 or houseState=4111");

            System.out.println("select h.*,b.no as bno from DGHouseInfo..house as h left join DGHouseInfo..Build as b on h.buildid=b.id" +
            " where houseState=890 or houseState=99 or houseState=127 or houseState=116 or houseState=115 or houseState=117 or houseState=119 or houseState=4111");

            while (setd.next()) {
                ResultSet setL = statementOwnerRecord.executeQuery("SELECT * FROM LOCKED_HOUSE WHERE HOUSE_CODE = '"+setd.getString("no")+"'");
                if(setL.next() == false) {
                    sqlWriter.newLine();
                    sqlWriter.write("INSERT LOCKED_HOUSE(HOUSE_CODE, DESCRIPTION, TYPE, EMP_CODE, EMP_NAME, LOCKED_TIME, ID, BUILD_CODE) values (" + Q.v(Q.p(setd.getString("no")), Q.lockHouseDescription(setd.getInt("houseState")), "'HOUSE_LOCKED'",
                            "'未知'", "'管理员'", Q.p(Q.nowFormatTime()), Q.p(setd.getString("no")), Q.p("N" + setd.getString("bno"))) + ");");
                    sqlWriter.flush();
                }

            }
            System.out.println("LOCKED_HOUSE is complate");
        } catch (Exception e) {
            System.out.println("LOCKED_HOUSE is errer");
            e.printStackTrace();
            return;
        }
    }
}
