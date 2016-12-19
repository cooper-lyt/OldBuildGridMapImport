package com.cooper.house;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cooper on 10/13/15.
 */
public class Q {


    public static String p(boolean v){
        if (v){
            return "TRUE";
        }else
            return "FALSE";
    }

    public static String filterErrorChar(String s){
        char[] cc = s.toCharArray();
        for(int i = 0; i < cc.length; i++){
            if(cc[i] == 0)
                cc[i] = ' ';
        }
        return String.valueOf(cc);
    }

    public static String p(String s){
        if (s == null) {
            return "NULL";
        }else{
            return "'" + filterErrorChar(s) + "'";
        }
    }

    public static String pm(String s){
        if (s == null) {
            return "'未知'";
        }else{
            return "'" + filterErrorChar(s) + "'";
        }
    }

    public static String pmId(String s){
        if (s == null) {
            return "''";
        }else{
            return "'" + s + "'";
        }
    }

    public static String p(java.sql.Timestamp d){
        if (d == null){
            return "NULL";
        }else{
            return "'" + d.toString() + "'";
        }
    }

    public static String pm(java.sql.Timestamp d){
        if (d == null){
            return "'2000-1-1'";
        }else{

            return "'" + d.toString() + "'";
        }
    }

    public static String pm(BigDecimal b){
        if (b == null) {
            return "0";
        }else{
            return b.stripTrailingZeros().toPlainString();
        }
    }

    public static String p(BigDecimal b){
        if (b == null){
            return "NULL";
        }else{
            return b.stripTrailingZeros().toPlainString();
        }
    }

    public static String pmw(String s , String dv){
        if (s == null){
            return "'" + dv + "'";
        }else{
            return "'" + s + "'";
        }
    }

    public static String pmwc(String s){
        if (s == null || s.trim().equals("2773") || s.trim().equals("205")){
            return "NULL";
        }else{
            return "'" + s + "'";
        }
    }

    public static String pCardType(int s){
        if( s==4 ){
            return "'MASTER_ID'";
        }else if( s==5 ){
            return "'SOLDIER_CARD'";
        }else if( s==6 ){
            return "'PASSPORT'";
        }else if( s==208 ){
            return "'OTHER'";
        }else if( s==1000 ) {
            return "'OTHER'";
        } else
            return "'OTHER'";
    }

    public static String changeHouseType(int HouseType){

        if( HouseType==205){
            return "'SALE_HOUSE'";
        }else if( HouseType==1946){
            return "'BACK_HOUSE'";
        }else if( HouseType==206){
            return "'WELFARE_HOUSE'";
        }else if( HouseType==2773 || HouseType==784){
            return "'OTHER'";
        }else if(HouseType==781) {
            return "'GOV_SALE_HOUSE'";
        } else if(HouseType==782){
            return "'GOV_RENT'";
        }else if(HouseType==783){
            return "'GROUP_HOUSE'";
        }else
            return "SALE_HOUSE";

    }

    public static String changePayType(int HouseType){
        if( HouseType==177 ){
            return "'ALL_PAY'";
        }else if( HouseType==178){
            return "'DEBIT_PAY'";
        }else if( HouseType==179){
            return "'PART_PAY'";
        }else
            return "NULL";
    }


    public static String changePoolMemo(int poolmemo){
        if( poolmemo==218){
            return "'TOGETHER_OWNER'";
        }else if( poolmemo==219){
            return "'SHARE_OWNER'";
        }else if( poolmemo==221){
            return "'SINGLE_OWNER'";
        }else if (poolmemo==222){
            return "'TOGETHER_OWNER'";
        }else
            return "NULL";
    }
    public static String changeBusinessEmpType(String jdname){
        if (jdname.equals("受理")){
            return "'APPLY_EMP'";
        }else if (jdname.equals("复审")){
            return "'FIRST_CHECK'";
        }else if (jdname.equals("审批")){
            return "'CHECK_EMP'";
        }else if (jdname.equals("归档")){
            return "'RECORD_EMP'";
        }else {
            return "'未知'";
        }
    }




    public static String lockHouseDescription(int HouseState){

        if( HouseState==99 ){
            return "'在老系统中房屋状态为：查封'";
        }else if( HouseState==116){
            return "'在老系统中房屋状态为：房屋已注销(灭籍)'";
        }else if( HouseState==115){
            return "'在老系统中房屋状态为：声明作废'";
        }else if( HouseState==119){
            return "'在老系统中房屋状态为：异议'";
        }else if(HouseState==890) {
            return "'在老系统中房屋状态为：在建工程抵押'";
        } else if(HouseState==117){
            return "'在老系统中房屋状态为：房屋状态为抵押'";
        }else if(HouseState==127) {
            return "'在老系统中房屋状态为：房屋状态为不可售'";
        }else
            return "'未知'";

    }

    public static String defineName(String defineID){

        if( defineID.equals("WP42") ){
            return "'商品房合同备案登记'";
        }else if( defineID.equals("WP43")){
            return "'撤销商品房合同备案登记'";
        }else
            return "'未知'";

    }

    public static String changeUseType(int UseType){

        if( UseType==0){
            return "'未知'";
        }else
            return "'"+String.valueOf(UseType)+"'";

    }

    public static String changeStructure(int Structure){

        if( Structure==0){
            return "'未知'";
        }else
            return "'"+String.valueOf(Structure)+"'";

    }

    public static String v(String... values){
        String result = null;
        for(String value: values){
            if (result == null){
                result = value;
            }else{
                result += "," + value;
            }
        }
        return result;

    }
    public static String nowFormatTime(){
        Date createTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(createTime);

    }

    public static String interest_type(String type){
        if (type.equals("按揭贷款")){
            return "'121'";
        }else if (type.equals("抵押贷款")){
            return "'122'";
        }else{
            return "'power.type.other'";
        }
    }


}
