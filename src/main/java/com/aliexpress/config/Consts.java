package com.aliexpress.config;

public interface Consts {

    public interface ErrCode {
        public static final int SUCCESS = 0;

        public static final int FAILED = -2;

        public static final int ILL_PARAMS = -3;
    }

    public interface Url {
        /**
         * 用户登录
         */
        public static final String USER_LOGIN = "/user/login";
        /**
         * 用户删除
         */
        public static final String USER_DEL = "/user/del";
        /**
         * 用户新增
         */
        public static final String USER_ADD = "/user/add";
        /**
         * 查询用户信息
         */
        public static final String USER_LIST = "/user/query/list";
        /**
         * 分类列表
         */
        public static final String TAG_LIST = "/tag/query/list";
        /**
         * 分类列表
         */
        public static final String TAG_BYID = "/tag/query/id";
        /**
         * 分类列表
         */
        public static final String TAG_ADD = "/tag/add";
        /**
         * 分类列表
         */
        public static final String TAG_DEL = "/tag/del";
        /**
         * 分类列表
         */
        public static final String TAG_EDIT = "/tag/edit";
        /**
         * 分类列表
         */
        public static final String GOODS_LIST = "/goods/query/list";
        /**
         * 分类列表
         */
        public static final String GOODS_STATUS = "/goods/query/status";
        /**
         * 分类列表
         */
        public static final String GOODS_DETAILS = "/goods/query/details";
        /**
         * 分类列表
         */
        public static final String GOODS_BYID = "/goods/query/id";
        /**
         * 分类列表
         */
        public static final String GOODS_ADD = "/goods/add";
        /**
         * 分类列表
         */
        public static final String GOODS_DEL = "/goods/del";
        /**
         * 分类列表
         */
        public static final String GOODS_EDIT = "/goods/edit";
        /**
         * 分类列表
         */
        public static final String GOODS_ADD_DETAILS = "/goods/details/add";
        /**
         * 分类列表
         */
        public static final String GOODS_DEL_DETAILS = "/goods/details/del";
        /**
         * 分类列表
         */
        public static final String GOODS_EDIT_DETAILS = "/goods/details/edit";
        /**
         * 分类列表
         */
        public static final String ADD_IMG = "/img/upload";
        /**
         * 分类列表
         */
        public static final String ADD_IMG_MAIN = "/img/upload/main";
        /**
         * 分类列表
         */
        public static final String IMG_BATCH_UPLOAD = "/img/batch/upload";
        /**
         * 分类列表
         */
        public static final String IMGLIST_BYIDS = "/img/query/imgIds";
        /**
         * 分类列表
         */
        public static final String IMG_BYID= "/img/query/imgId";
        /**
         * 分类列表
         */
        public static final String IMG_DOWNBYID= "/img/download/imgIds";
    }


}
