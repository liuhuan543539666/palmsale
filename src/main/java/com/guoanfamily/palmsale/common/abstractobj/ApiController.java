package com.guoanfamily.palmsale.common.abstractobj;

public abstract class ApiController {
    private static final String API_PATH = "/api/v1";

    public static final String AUTHENTICATE_URL = API_PATH + "/authenticate";
    public static final String PARTNER_URL = API_PATH + "/partner";
    public static final String USER_URL = API_PATH + "/user";
    public static final String ROLE_URL = API_PATH + "/role";
    public static final String MENU_URL = API_PATH + "/menu";
    public static final String DICT_URL = API_PATH + "/dict";
    public static final String CUST_URL = API_PATH + "/custom";
    public static final String Build_URL = API_PATH + "/build";
    public static final String Agent_URL = API_PATH + "/agent";
    public static final String DEPART_URL = API_PATH + "/depart";
    public static final String CUSTOMSALLOCATE_URL = API_PATH + "/customsallocate";
    public static final String CUSTOMERDEALINFO_URL = API_PATH + "/customerdealinfo";
    public static final String KNOWLEDGE_URL = API_PATH + "/knowledge";
    public static final String KNOWLEDGEBROWSE_URL = API_PATH + "/knowledgebrowse";
    public static final String HOTARTICLE_URL = API_PATH + "/hotarticle";
    public static final String PAYMONEY_URL = API_PATH + "/paymoney";
    public static final String CUSTMANAGER_URL = API_PATH + "/customManage";
    public static final String COUNT_URL = API_PATH + "/count";
    public static final String Brower_URL = API_PATH + "/brower";
    public static final String Share_URL = API_PATH + "/share";
    // Spring Boot Actuator services
    public static final String HEALTH_ENDPOINT = "/health";
    public static final String AUTOCONFIG_ENDPOINT = "/autoconfig";
    public static final String BEANS_ENDPOINT = "/beans";
    public static final String CONFIGPROPS_ENDPOINT = "/configprops";
    public static final String ENV_ENDPOINT = "/env";
    public static final String MAPPINGS_ENDPOINT = "/mappings";
    public static final String METRICS_ENDPOINT = "/metrics";
    public static final String SHUTDOWN_ENDPOINT = "/shutdown";
}
