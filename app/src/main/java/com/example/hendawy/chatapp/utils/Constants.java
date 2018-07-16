package com.example.hendawy.chatapp.utils;

public class Constants {

    public static final String UI_NEW_MESSAGE = "com.example.hendawy.chatapp.ui_new_message";

    public static final String UI_CONNECTION_STATUS_CHANGE = "com.example.hendawy.chatapp.connection_status_change";
    public static final String ONLINE_STATUS_CHANGE_CONTACT = "com.example.hendawy.chatapp.online_status_change_contact";

    public static final class BroadCastMessages {

        public static final String UI_AUTHENTICATED = "com.example.hendawy.chatapp.uiauthenticated";
        public static final String UI_CONNECTION_ERROR = "com.example.hendawy.chatapp.ui_connection_error";
        public static final String UI_CONNECTION_STATUS_CHANGE_FLAG = "com.example.hendawy.chatapp.connection_status_change_flag";
        public static final String UI_NEW_MESSAGE_FLAG = "com.example.hendawy.chatapp.ui_new_message_flag";
        public static final String UI_NEW_CHAT_ITEM = "com.example.hendawy.chatapp.ui_new_chat_item";
        public static final String UI_ONLINE_STATUS_CHANGE = "com.example.hendawy.chatapp.ui_online_status_change";
    }

    //Email Validation pattern
    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    //Fragments Tags
    public static final String Login_Fragment = "Login_Fragment";
    public static final String SignUp_Fragment = "SignUp_Fragment";
    public static final String ForgotPassword_Fragment = "ForgotPassword_Fragment";
}
