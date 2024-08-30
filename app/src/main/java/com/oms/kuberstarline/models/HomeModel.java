package com.oms.kuberstarline.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModel {
    @SerializedName("withdraw_status")
    @Expose
    private Integer withdrawStatus;
    @SerializedName("wallet_amt")
    @Expose
    private String walletAmt;
    @SerializedName("transfer_point_status")
    @Expose
    private String transferPointStatus;
    @SerializedName("betting_status")
    @Expose
    private String bettingStatus;
    @SerializedName("account_block_status")
    @Expose
    private String accountBlockStatus;
    @SerializedName("device_result")
    @Expose
    private List<DeviceResult> deviceResult;
    @SerializedName("result")
    @Expose
    private List<Result> result;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Integer getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(Integer withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public String getWalletAmt() {
        return walletAmt;
    }

    public void setWalletAmt(String walletAmt) {
        this.walletAmt = walletAmt;
    }

    public String getTransferPointStatus() {
        return transferPointStatus;
    }

    public void setTransferPointStatus(String transferPointStatus) {
        this.transferPointStatus = transferPointStatus;
    }

    public String getBettingStatus() {
        return bettingStatus;
    }

    public void setBettingStatus(String bettingStatus) {
        this.bettingStatus = bettingStatus;
    }

    public String getAccountBlockStatus() {
        return accountBlockStatus;
    }

    public void setAccountBlockStatus(String accountBlockStatus) {
        this.accountBlockStatus = accountBlockStatus;
    }

    public List<DeviceResult> getDeviceResult() {
        return deviceResult;
    }

    public void setDeviceResult(List<DeviceResult> deviceResult) {
        this.deviceResult = deviceResult;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public class Result {

        @SerializedName("game_id")
        @Expose
        private String gameId;
        @SerializedName("game_name")
        @Expose
        private String gameName;
        @SerializedName("game_name_hindi")
        @Expose
        private String gameNameHindi;
        @SerializedName("open_time")
        @Expose
        private String openTime;
        @SerializedName("open_time_sort")
        @Expose
        private String openTimeSort;
        @SerializedName("close_time")
        @Expose
        private String closeTime;
        @SerializedName("game_name_letter")
        @Expose
        private String gameNameLetter;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("msg_status")
        @Expose
        private Integer msgStatus;
        @SerializedName("open_result")
        @Expose
        private String openResult;
        @SerializedName("close_result")
        @Expose
        private String closeResult;
        @SerializedName("open_duration")
        @Expose
        private Integer openDuration;
        @SerializedName("close_duration")
        @Expose
        private Integer closeDuration;
        @SerializedName("time_srt")
        @Expose
        private Integer timeSrt;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getGameNameHindi() {
            return gameNameHindi;
        }

        public void setGameNameHindi(String gameNameHindi) {
            this.gameNameHindi = gameNameHindi;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getOpenTimeSort() {
            return openTimeSort;
        }

        public void setOpenTimeSort(String openTimeSort) {
            this.openTimeSort = openTimeSort;
        }

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getGameNameLetter() {
            return gameNameLetter;
        }

        public void setGameNameLetter(String gameNameLetter) {
            this.gameNameLetter = gameNameLetter;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getMsgStatus() {
            return msgStatus;
        }

        public void setMsgStatus(Integer msgStatus) {
            this.msgStatus = msgStatus;
        }

        public String getOpenResult() {
            return openResult;
        }

        public void setOpenResult(String openResult) {
            this.openResult = openResult;
        }

        public String getCloseResult() {
            return closeResult;
        }

        public void setCloseResult(String closeResult) {
            this.closeResult = closeResult;
        }

        public Integer getOpenDuration() {
            return openDuration;
        }

        public void setOpenDuration(Integer openDuration) {
            this.openDuration = openDuration;
        }

        public Integer getCloseDuration() {
            return closeDuration;
        }

        public void setCloseDuration(Integer closeDuration) {
            this.closeDuration = closeDuration;
        }

        public Integer getTimeSrt() {
            return timeSrt;
        }

        public void setTimeSrt(Integer timeSrt) {
            this.timeSrt = timeSrt;
        }

    }

    public class DeviceResult {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("device_id")
        @Expose
        private String deviceId;
        @SerializedName("logout_status")
        @Expose
        private String logoutStatus;
        @SerializedName("security_pin_status")
        @Expose
        private String securityPinStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getLogoutStatus() {
            return logoutStatus;
        }

        public void setLogoutStatus(String logoutStatus) {
            this.logoutStatus = logoutStatus;
        }

        public String getSecurityPinStatus() {
            return securityPinStatus;
        }

        public void setSecurityPinStatus(String securityPinStatus) {
            this.securityPinStatus = securityPinStatus;
        }

    }


}
