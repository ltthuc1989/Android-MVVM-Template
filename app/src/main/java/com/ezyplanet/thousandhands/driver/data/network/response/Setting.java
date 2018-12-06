package com.ezyplanet.thousandhands.driver.data.network.response;

/**
 * Created by PV on 9/9/2016.
 */
public class Setting {

    /**
     * HELPER_SERVICE_FEE : 20
     * REFERRAL_EARNINGS_PERCENTAGE : 2
     * CUSTOMER_SERVICE_FEE : 0
     * FAYE_URL : http://localhost:3000/faye
     */


    private int HELPER_SERVICE_FEE;
    private int REFERRAL_EARNINGS_PERCENTAGE;
    private int CUSTOMER_SERVICE_FEE;
    private String socket_io_endpoint;
    private String FAYE_URL;
    private CompanyInfo company_info;


    Object states;
    /**
     * book_time_package : {"times":6,"month":24}
     */

    private BookTimePackageBean book_time_package;
    /**
     * green_store : {"latitude":10.804337,"longitude":106.6860123,"address":"12 Lam sơn, P.6, Q.Bình Thạnh, HCM"}
     */

    private GreenStoreBean green_store;
    /**
     * payment_info : {"onepay":1,"vnpay":1}
     */

    private PaymentInfoBean payment_info;





    private boolean use_payment_cash;

    public Object getStates() {
        return states;
    }

    public void setStates(Object states) {
        this.states = states;
    }

    public int getHELPER_SERVICE_FEE() {
        return HELPER_SERVICE_FEE;
    }

    public void setHELPER_SERVICE_FEE(int HELPER_SERVICE_FEE) {
        this.HELPER_SERVICE_FEE = HELPER_SERVICE_FEE;
    }

    public int getREFERRAL_EARNINGS_PERCENTAGE() {
        return REFERRAL_EARNINGS_PERCENTAGE;
    }

    public void setREFERRAL_EARNINGS_PERCENTAGE(int REFERRAL_EARNINGS_PERCENTAGE) {
        this.REFERRAL_EARNINGS_PERCENTAGE = REFERRAL_EARNINGS_PERCENTAGE;
    }

    public int getCUSTOMER_SERVICE_FEE() {
        return CUSTOMER_SERVICE_FEE;
    }

    public void setCUSTOMER_SERVICE_FEE(int CUSTOMER_SERVICE_FEE) {
        this.CUSTOMER_SERVICE_FEE = CUSTOMER_SERVICE_FEE;
    }

    public String getFAYE_URL() {
        return FAYE_URL;
    }

    public void setFAYE_URL(String FAYE_URL) {
        this.FAYE_URL = FAYE_URL;
    }

    public CompanyInfo getCompany_info() {
        return company_info;
    }

    public void setCompany_info(CompanyInfo company_info) {
        this.company_info = company_info;
    }

    public String getSocket_io_endpoint() {
        return socket_io_endpoint;
    }

    public void setSocket_io_endpoint(String socket_io_endpoint) {
        this.socket_io_endpoint = socket_io_endpoint;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "HELPER_SERVICE_FEE=" + HELPER_SERVICE_FEE +
                ", REFERRAL_EARNINGS_PERCENTAGE=" + REFERRAL_EARNINGS_PERCENTAGE +
                ", CUSTOMER_SERVICE_FEE=" + CUSTOMER_SERVICE_FEE +
                ", FAYE_URL='" + FAYE_URL + '\'' +
                ", company_info=" + company_info +
                '}';
    }


    public boolean isUse_payment_cash() {
        return use_payment_cash;
    }

    public void setUse_payment_cash(boolean use_payment_cash) {
        this.use_payment_cash = use_payment_cash;
    }

    public BookTimePackageBean getBook_time_package() {
        return book_time_package;
    }

    public void setBook_time_package(BookTimePackageBean book_time_package) {
        this.book_time_package = book_time_package;
    }

    public GreenStoreBean getGreen_store() {
        return green_store;
    }

    public void setGreen_store(GreenStoreBean green_store) {
        this.green_store = green_store;
    }

    public PaymentInfoBean getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(PaymentInfoBean payment_info) {
        this.payment_info = payment_info;
    }

    public static class BookTimePackageBean {
        /**
         * times : 6
         * month : 24
         */

        private int times;
        private int month;

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }
    }

    public static class GreenStoreBean {
        /**
         * latitude : 10.804337
         * longitude : 106.6860123
         * address : 12 Lam sơn, P.6, Q.Bình Thạnh, HCM
         */

        private double latitude;
        private double longitude;
        private String address;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class PaymentInfoBean {
        /**
         * onepay : 1
         * vnpay : 1
         */

        private int onepay;
        private int vnpay;

        public int getOnepay() {
            return onepay;
        }

        public void setOnepay(int onepay) {
            this.onepay = onepay;
        }

        public int getVnpay() {
            return vnpay;
        }

        public void setVnpay(int vnpay) {
            this.vnpay = vnpay;
        }
    }
    public boolean enableVNPAY(){
       return (payment_info.vnpay==1);

    }
}
