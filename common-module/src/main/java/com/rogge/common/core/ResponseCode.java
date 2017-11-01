package com.rogge.common.core;

import com.rogge.common.annotation.Description;
import com.rogge.common.model.LanguageEnum;

/**
 * 返回码<br>
 * <ul>
 * <li>可使用{@link #getCode()}获取Integer类型的状态码，或</li>
 * <li>可使用{@link #getExplain(LanguageEnum language)}获取错误原因说明</li>
 * <li>使用{@link #toString()}字符串类型的状态码</li>
 * </ul>
 * 本接口含有以下enum类：
 * <ul>
 * <li>{@link Base}基础返回码，码段(0~99)</li>
 * <li>{@link Parameter}参数类，码段(110-199)</li>
 * <li>{@link LoginRegister}登录权限类，码段(310-399)</li>
 * <p>
 * <li>{@link Card}卡片模块，码段(1000-1999)</li>
 * <li>{@link Service}服务模块，码段(2000-2999)</li>
 * <li>{@link Access}设计师模块，码段(3000-3999)</li>
 * </ul>
 *
 * @author Anty
 */
public interface ResponseCode {

    /**
     * @return 状态码
     */
    int getCode();

    /**
     * @return 状态码对应的解释。
     */
    String getExplain(LanguageEnum language);

    /**
     * @return 将状态码转换为字符串返回。
     */
    String toString();

    /**
     * 基础返回码(0-99)
     */
    @Description("基础返回码(0-99)")
    public enum Base implements ResponseCode {
        /**
         * 基础返回码--请求成功
         */
        SUCCESS(0, "请求成功", "Success"),
        /**
         * 基础返回码--错误
         */
        ERROR(1, "错误", "Error"),
        /**
         * 基础返回码--请求超时
         */
        REQUEST_TIMEOUT(2, "请求超时", "Request timeout"),
        /**
         * 基础返回码--系统通讯异常
         */
        NET_ERR(3, "系统通讯异常", "Net error"),
        /**
         * 基础返回码--接口不存在
         */
        API_NO_EXISTS(4, "接口不存在", "API NO EXISTS"),
        /**
         * 基础返回码--接口异常
         */
        API_ERR(5, "接口异常", "API error"),
        /**
         * 基础返回码--MybatisTooManyException
         */
        TOO_MANY_EXCEP(6, "查询结果不唯一", "TOO MANY Exception"),
        /**
         * 基础返回码--系统错误
         */
        SYSTEM_ERR(10, "系统繁忙，请稍候再试", "System is busy, please try again later");

        private final Integer code;
        private String zhExplain;
        private String enExplain;

        private Base(int code) {
            this.code = code;
        }

        private Base(Integer code, String zhExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
        }

        private Base(Integer code, String zhExplain, String enExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
            this.enExplain = enExplain;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code.toString();
        }

        @Override
        public String getExplain(LanguageEnum language) {
            if (language == LanguageEnum.zh_CN) {
                return zhExplain;
            } else {
                return enExplain;
            }
        }

    }

    /**
     * 请求参数错误提示返回码
     */
    @Description("请求参数错误提示返回码(1xx)")
    public enum Parameter implements ResponseCode {
        /**
         * 返回码--传入参数类--参数为空
         */
        NULL(101, "传入参数不能为空", "Incoming parameters cannot be empty"),
        /**
         * 返回码--传入参数类--无法解析
         */
        CANNOT_PARSE(102, "传入参数无法解析", "Unable to parse the incoming parameters"),
        /**
         * 返回码--传入参数类--格式错误
         */
        FORMAT_ERR(103, "传入参数格式错误", "Incoming parameters format error"),
        /**
         * 返回码--传入参数类--缺少必要参数
         */
        LACK(104, "缺少必要参数", "Missing Parameters "),
        /**
         * 返回码--传入参数类--参数值不合法
         */
        ILLEGAL(105, "传入参数值不合法", "Incoming parameters values are not legal"),
        /**
         * 返回码--传入参数类--其他错误
         */
        OTHERS(199, "参数错误", "Parameters error");

        private final Integer code;
        private String zhExplain;
        private String enExplain;

        private Parameter(int code) {
            this.code = code;
        }

        private Parameter(Integer code, String zhExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
        }

        private Parameter(Integer code, String zhExplain, String enExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
            this.enExplain = enExplain;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code.toString();
        }

        @Override
        public String getExplain(LanguageEnum language) {
            if (language == LanguageEnum.zh_CN) {
                return zhExplain;
            } else {
                return enExplain;
            }
        }
    }

    /**
     * 用户登录权限相关(310~399)
     */
    @Description("用户登录权限相关(310~399)")
    public enum LoginRegister implements ResponseCode {
        /**
         * 返回码--用户登录注册--手机号码不能为空
         */
        PHONE_EMPTY(310, "手机号码不能为空", "Phone number cannot be empty"),
        /**
         * 返回码--用户登录注册--密码不能为空
         */
        PASSWORD_EMPTY(311, "密码不能为空", "Password cannot be empty"),
        /**
         * 返回码--用户登录注册--用户不存在
         */
        USER_NO_EXISTS(312, "用户不存在", "The user does not exist"),
        /**
         * 返回码--用户登录注册--用户名已锁定
         */
        USER_LOCKED(313, "用户已锁定", "The user has been locked"),
        /**
         * 返回码--用户登录注册--用户名或密码错误，请重新输入
         */
        PWD_INPUT_ERROR(314, "用户名或密码错误，请重新输入", "User name or password error, please re-enter"),
        /**
         * 返回码--用户登录注册--密码输入错误次数已超过上限
         */
        PWD_INPUT_ERROR_LIMIT(315, "密码输入错误超过上限，请${time}分钟后重新输入", "Password error more than limit, please re-enter ${time} minutes"),
        /**
         * 返回码--用户登录注册--第三方账号未注册(补充注册时使用，不要提示文字)
         */
        THIRD_NO_REGISTER(316, "第三方账号未注册", "The third party account registered"),
        /**
         * 返回码--用户登录注册--手机号码已注册
         */
        PHONE_EXISTS(317, "手机号码已注册", "Registered mobile phone number"),
        /**
         * 返回码--用户登录注册--未登录
         */
        NOLOGIN(318, "未登录或登陆超时", "Not login or login timeout"),
        /**
         * 返回码--用户登录注册--昵称不能为空
         */
        NICKNAME_EMPTY(319, "昵称不能为空", "nick cannot be empty"),
        /**
         * 返回码--用户登录注册--国际码不能为空
         */
        SMSCODE_EMPTY(320, "国际码不能为空", "The international code cannot be empty"),
        /**
         * 返回码--用户登录注册--手机号码不合法
         */
        PHONE_NO_VALID(321, "手机号码不合法", "Phone number is no valid"),

        /**
         * 返回码--用户登录注册--密码不合法
         */
        PASSWORD_NO_VALID(322, "密码不合法", "Password is no valid"),

        /**
         * 返回码--用户登录注册--昵称不合法
         */
        NICK_NAME_NO_VALID(323, "昵称不合法", "Nick name is no valid"),
        /**
         * 返回码--用户登录注册--头像不能为空
         */
        HEADICON_EMPTY(324, "头像不能为空", "head icon cannot be empty"),
        /**
         * 返回码--忘记密码--验证码不能为空
         */
        VERIFY_CODE(325, "图形验证码不能为空", "verify code cannot be empty"),
        /**
         * 返回码--忘记密码--验证码错误
         */
        VERIFY_CODE_ERROR(326, "图形验证码错误", "verify code error"),
        /**
         * 返回码--获取desKey--获取失败
         */
        GET_KEY_ERROR(327, "获取Key失败", "get key fail"),;

        private final Integer code;
        private String zhExplain;
        private String enExplain;

        private LoginRegister(int code) {
            this.code = code;
        }

        private LoginRegister(Integer code, String zhExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
        }

        private LoginRegister(Integer code, String zhExplain, String enExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
            this.enExplain = enExplain;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code.toString();
        }

        @Override
        public String getExplain(LanguageEnum language) {
            if (language == LanguageEnum.zh_CN) {
                return zhExplain;
            } else {
                return enExplain;
            }
        }
    }

    /**
     * 卡片返回码(0-99)
     */
    @Description("基础返回码(1000-1999)")
    public enum Card implements ResponseCode {
        /**
         * 返回码--卡片组--卡片组没找到
         */
        CARD_GROUP_ERR(1000, "卡片组信息为空", "card group not found"),

        /**
         * 返回码--卡片组--卡片组没找到
         */
        CARD_LIST_ERR(1001, "卡片列表为空", "card list not found");

        private final Integer code;
        private String zhExplain;
        private String enExplain;

        private Card(int code) {
            this.code = code;
        }

        private Card(Integer code, String zhExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
        }

        private Card(Integer code, String zhExplain, String enExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
            this.enExplain = enExplain;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code.toString();
        }

        @Override
        public String getExplain(LanguageEnum language) {
            if (language == LanguageEnum.zh_CN) {
                return zhExplain;
            } else {
                return enExplain;
            }
        }

    }

    /**
     * 卡片返回码(2000-2999)
     */
    @Description("基础返回码(0-99)")
    public enum Service implements ResponseCode {
        /**
         * 返回码--服务--服务列表没找到
         */
        SERVICE_LIST_ERR(2000, "服务列表为空", "service list not found");

        private final Integer code;
        private String zhExplain;
        private String enExplain;

        private Service(int code) {
            this.code = code;
        }

        private Service(Integer code, String zhExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
        }

        private Service(Integer code, String zhExplain, String enExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
            this.enExplain = enExplain;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code.toString();
        }

        @Override
        public String getExplain(LanguageEnum language) {
            if (language == LanguageEnum.zh_CN) {
                return zhExplain;
            } else {
                return enExplain;
            }
        }

    }

    /**
     * 客户件返回码(3000-3999)
     */
    @Description("客户件返回码(3000-3999)")
    public enum Access implements ResponseCode {
        /**
         * 返回码--客户件--没有查询到待进件详情
         */
        WAIT_DETAIL_ERR(3000, "没有查询到待进件详情", "xxxxxxxxx"),

        /**
         * 返回码--客户件--没有查询到待进件详情
         */
        ACCESS_LIST_ERR(3001, "没有查询到件列表", "xxxxxxxxx"),

        /**
         * 返回码--提示--没有查询到提示信息
         */
        ACCESS_TIPS_ERR(3002, "没有查询到提示信息", "xxxxxxxxx"),

        /**
         * 返回码--影像采集--没有查询到影像配置参数
         */
        VIDEO_PARAM_ERR(3003, "没有查询到影像配置参数", "xxxxxxxxx"),

        /**
         * 返回码--影像采集--没有查询到进件参数
         */
        VIDEO_LIST_ERR(3004, "没有查询到进件参数", "xxxxxxxxx"),

        /**
         * 返回码--上传--上传图片失败
         */
        UPLOAD_PHOTO_ERR(3005, "上传图片失败", "xxxxxxxxx"),

        /**
         * 返回码--销售渠道代码--没有查询到销售渠道代码
         */
        SALE_CODE_ERR(3010, "获取渠道数据失败", "xxxxxxxxx");

        private final Integer code;
        private String zhExplain;
        private String enExplain;

        private Access(int code) {
            this.code = code;
        }

        private Access(Integer code, String zhExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
        }

        private Access(Integer code, String zhExplain, String enExplain) {
            this.code = code;
            this.zhExplain = zhExplain;
            this.enExplain = enExplain;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code.toString();
        }

        @Override
        public String getExplain(LanguageEnum language) {
            if (language == LanguageEnum.zh_CN) {
                return zhExplain;
            } else {
                return enExplain;
            }
        }

    }

}
