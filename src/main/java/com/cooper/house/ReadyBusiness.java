package com.cooper.house;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cooper on 10/13/15.
 */
public class ReadyBusiness {


    public final static Map<String,Integer> map = new HashMap<String,Integer>();
    static {
        map.put("CONTRACTS_RECORD", 128);
        map.put("SALE_REGISTER", 129);
        map.put("DIVERT_REGISTER", 130);
        map.put("SALE_MORTGAGE_REGISTER", 132);
        map.put("DIVERT_MORTGAGE_REGISTER", 133);
        map.put("PLEDGE", 117);
        map.put("PROJECT_PLEDGE", 890);
        map.put("DIFFICULTY", 119);
        map.put("DECLARE_CANCEL", 115);
        map.put("COURT_CLOSE", 99);
        map.put("DESTROY", 116);
        map.put("OWNERED",120);

    }


    private static final String[] NO_RECORD = {
            "WP42","WP43","WP50","WP51","WP85"
    };

    private static final String[] BEFOR_POOL = {

            "WP9",
            "WP10",
            "WP11",
            "WP12",
            "WP13",
            "WP14",
            "WP15",
            "WP16",
            "WP17",

            "WP25",
            "WP26",

            "WP73",
            "WP74",

            "WP35",
            "WP36",
            "WP37",

            "WP48",
            "WP49",
            "WP22",
            "WP23",
            "WP24",

            "WP45",

            "WP1",
            "WP2",
            "WP3",
            "WP4",
            "WP5",
            "WP6",
            "WP7",
            "WP8" };




    private static final List<String> NO_RECORD_LIST = Arrays.asList(NO_RECORD);

    private static final List<String> BEFOR_POOL_LIST = Arrays.asList(BEFOR_POOL);

    private Map<String ,String> poolOwner = new HashMap<String, String>();

    private ReadyBusiness befor;

    private ReadyBusiness after;

    private String id;

    private String memo;

    private String status;

    private String selectBusiness;

    private String defineName;

    private String defineId;

    private String otherBizInfo = "";

    public void setOtherBizInfo(String otherBizInfo) {
        this.otherBizInfo = otherBizInfo;
    }

    private java.sql.Timestamp applyTime;

    private java.sql.Timestamp  checkTime;

    private java.sql.Timestamp  regTime;

    private java.sql.Timestamp  recordTime;

    private String house;

    private String oldOwnerId;

    private String oldCardId;

    private String owner;


    private String developerOwner;

    private String developerOwnerId;

    private String contractOwner;

    private String contractOwnerId;

    private String noticeOwner;

    private String noticeOwnerId;

    public String getDeveloperOwnerId() {
        return developerOwnerId;
    }

    public String getContractOwnerId() {
        return contractOwnerId;
    }

    public String getNoticeOwnerId() {
        return noticeOwnerId;
    }

    public void setDeveloperOwner(String id,String owner){
        if (id == null){
            if (befor != null && befor.getDeveloperOwnerId() != null){
                developerOwnerId = befor.getDeveloperOwnerId();
                developerOwner = null;
            }else {
                developerOwnerId = null;
                developerOwner = null;
            }
        }else if(id == ""){
            developerOwnerId = null;
            developerOwner = null;
        }else{
            developerOwnerId = id;
            developerOwner = owner;
        }
    }

    public void setContractOwner(String id,String owner){
        if (id == null){
            if (befor != null && befor.getContractOwnerId() != null){
                contractOwnerId = befor.getContractOwnerId();
                contractOwner = null;
            }else {
                contractOwnerId = null;
                contractOwner = null;
            }
        }else if(id == ""){
            contractOwnerId = null;
            contractOwner = null;
        }else{
            contractOwnerId = id;
            contractOwner = owner;
        }
    }


    public void setNoticeOwner(String id,String owner){
        if (id == null){
            if (befor != null && befor.getNoticeOwnerId() != null){
                noticeOwnerId = befor.getDeveloperOwnerId();
            }else {
                noticeOwnerId = null;
            }
            noticeOwner = null;
        }else if(id == ""){
            noticeOwnerId = null;
            noticeOwner = null;
        }else{
            noticeOwnerId = id;
            noticeOwner = owner;
        }
    }

    private String mortgaeg;

    private String startHouse ;

    private String houseCode;

    public void setMortgaeg(String mortgaeg) {
        this.mortgaeg = mortgaeg;
    }

    public void setStartHouse(String startHouse) {
        this.startHouse = startHouse;
    }

    public void putPoolOwner(String id,String poolOwner) {
        this.poolOwner.put(id,poolOwner);
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setApplyTime(java.sql.Timestamp  applyTime) {
        this.applyTime = applyTime;
    }

    public void setDefineName(String defineName) {
        this.defineName = defineName;
    }

    public void setOwnerId(String oldOwnerId, String oldCardId, String owner,boolean takeLast) {
        this.oldCardId = oldCardId;
        this.oldOwnerId = oldOwnerId;
        if (oldOwnerId == null){
            newOwnerId = null;
            this.owner = null;

        }else if (befor != null  &&  befor.oldOwnerId!= null &&  befor.oldOwnerId.equals(oldOwnerId) &&
                ( takeLast ||

                ((befor.oldCardId == null && oldCardId == null) || (befor.oldCardId != null && befor.oldCardId.equals(oldCardId)))

                )
                ){
            newOwnerId = befor.newOwnerId;
            this.owner = null;

        }else{
            newOwnerId = this.id;
            this.owner = owner;

        }
    }

    private String newOwnerId;

    public String getNewOwnerId() {
        return newOwnerId;
    }

    public String getDefineId() {
        return defineId;
    }

    public String getId() {
        return id;
    }

    private List<HouseStatus> houseStates;

    public String getMainStatus(){

        if (houseStates.isEmpty()){
            return "NULL";
        }else{
            Collections.sort(houseStates, HouseStatus.StatusComparator.getInstance());
            return "'" + houseStates.get(0).toString() + "'";
        }

    }

    public Integer getOldStatus(){

        if (!houseStates.isEmpty()){

            Collections.sort(houseStates, HouseStatus.StatusComparator.getInstance());
            return map.get(houseStates.get(0).toString());
        }
        return null;
    }

    private String statusSql = "";

    private boolean out ;

    private String nameId;

    public ReadyBusiness(boolean out,String houseCode, ReadyBusiness start, String workId, String id, String memo, String selectBusiness, java.sql.Timestamp  checkTime, java.sql.Timestamp  regTime, java.sql.Timestamp  recordTime, String nameId) {

        if (start != null) {
            start.after = this;
            this.befor = start;
            houseStates = new ArrayList<HouseStatus>(start.houseStates);
        }else {
            houseStates = new ArrayList<HouseStatus>();
        }

        this.nameId = nameId;
        this.houseCode = houseCode;
        this.id = id;
        this.memo = memo;
        this.selectBusiness = selectBusiness;
        this.checkTime = checkTime;
        this.regTime = regTime;
        this.recordTime = recordTime;
        this.out = out;

        String[] temp = workId.split("_");

        defineId = temp[temp.length-1];
        status = "COMPLETE";

        System.out.println(id);



        HouseState state = FillHouseState.fillHouseState(defineId + ",", workId.contains("A"));

        if (state != null){
            HouseStatus status = HouseStatus.valueOf(state.getState());

            if (state.isEnable()){
                if (status.isAllowRepeat() || !houseStates.contains(status)){
                    houseStates.add(status);

                }


            }else{
                houseStates.remove(status);

            }
            statusSql = "INSERT INTO ADD_HOUSE_STATUS(ID,BUSINESS,STATUS,IS_REMOVE) VALUES("+

                    Q.v( Q.p(id), Q.p(id) , Q.p(state.getState()) , Q.p(!state.isEnable()) )

                    +");";
        }



    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String run(){
        if (befor == null){
            return genHouseBusinessSql();
        }else{
            return befor.run();
        }
    }

    private String genHouseBusinessSql(){
        String result = "";
        if (out) {

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            result =  "\n INSERT INTO OWNER_BUSINESS(ID,VERSION,SOURCE,MEMO,STATUS,DEFINE_NAME,DEFINE_ID,DEFINE_VERSION,SELECT_BUSINESS,CREATE_TIME,APPLY_TIME,CHECK_TIME,REG_TIME,RECORD_TIME,RECORDED,TYPE) VALUES(" +
                    Q.v(Q.p(id), "1", "'BIZ_IMPORT'", Q.p(memo), Q.p(status), Q.p(defineName), Q.p(defineId), "NULL", Q.p(selectBusiness), Q.p(f.format(new Date())),
                            Q.p(applyTime == null ? recordTime : applyTime), Q.p(checkTime == null ? recordTime : checkTime), Q.p(regTime == null ? recordTime : regTime), Q.p(recordTime), NO_RECORD_LIST.contains(getDefineId()) ? "FALSE" : "TRUE", "'NORMAL_BIZ'") +
                    ");";
            if (owner != null)
                result += owner;

            if (mortgaeg != null) {
                result += mortgaeg;
            }

            if (befor == null) {
                result += startHouse;
            }

            result += house;



            result +=   "\n INSERT INTO BUSINESS_HOUSE(ID,HOUSE_CODE,BUSINESS_ID,START_HOUSE,AFTER_HOUSE,CANCELED) VALUES("

                    + Q.v(Q.p(id), Q.p(houseCode), Q.p(id), Q.p((befor == null) ? id + "-s" : befor.id),
                    Q.p(id), "FALSE"
            ) +
                    ");\n";


            result += statusSql;

            if (!poolOwner.isEmpty()) {
                for (String pool : poolOwner.values()) {
                    result += pool;
                }
            }
            result += linkPoolOwner(id);

            result += "DELETE FROM LOCKED_HOUSE WHERE HOUSE_CODE =" + Q.p(houseCode) + " AND TYPE = 'SYSTEM_LOCKED';";

            result += otherBizInfo;

            if (developerOwner != null){
                result += developerOwner;
            }

            if (developerOwnerId != null){
                result += "UPDATE HOUSE set OLD_OWNER = " + Q.p(developerOwnerId) + " where ID = " + Q.p(id) + ";";
            }

            if (contractOwner != null){
                result += contractOwner;
            }

            if (contractOwnerId != null){
                result += "UPDATE HOUSE set CONTRACT_OWNER = " + Q.p(contractOwnerId) + " where ID = " + Q.p(id) + ";";
            }

            if (noticeOwner != null){
                result += noticeOwner;
            }


            if (noticeOwnerId != null){
                result += "UPDATE HOUSE set NOITCE_OWNER = " + Q.p(noticeOwnerId) + " where ID = " + Q.p(id) + ";";
            }
        }

        if (after != null){
            result += after.genHouseBusinessSql();
        }else if (out){
            result += "DELETE FROM HOUSE_RECORD WHERE HOUSE_CODE = " + Q.p(houseCode)

                    +
            " ;  INSERT INTO HOUSE_RECORD(HOUSE_CODE,HOUSE,HOUSE_STATUS) VALUES("+

                    Q.v(Q.p(houseCode), Q.p(id), getMainStatus())

                    +");";
        }



        return result;
    }

    private String linkPoolOwner(String bizID){
        String result = "";
        if (!poolOwner.isEmpty()){
            for(Map.Entry<String,String> entry: poolOwner.entrySet()){
                result += "INSERT INTO HOUSE_POOL(HOUSE, POOL) VALUES(" + Q.v(

                        Q.p(bizID),Q.p(entry.getKey())

                ) + ");";
            }
        }else if (befor != null && BEFOR_POOL_LIST.contains(getDefineId())){
            result = befor.linkPoolOwner(bizID);
        }

        return result;


    }


    //



 //   保留上一个共有权人



}
