package com.example.gatewayservice.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @author lyn
 * TODO zuul全局网关安全过滤器
 * @date 2020/10/12 10:13
*/
public class MyGlobalZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //操作request
        //过滤js 注入脚本
        //过滤SQL注入脚本
        //检查请求是否携带token  配置查询Redis 中的token是否有
        //检查referer请求头
        //根据ip地址限流保护 Redis中维护每个ip地址每秒发送的请求数量 1秒超过多少请求拒绝访问
        if (checkAttack(request)){
            // 返回信息
            currentContext.setResponseBody("注入拦截");
            // 跳转路径
            request.getRequestDispatcher("/erro");
            // 对该请求禁止路由，禁止访问下游服务
            currentContext.setSendZuulResponse(false);
        }

        return null;
    }

    private boolean checkAttack(HttpServletRequest request){

        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                "chr|mid|master|truncate|char|declare|or|;|--|,|like|//|/|%|#";
        String[] badStrs = badStr.split("\\|");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
            String [] strings=new String[stringEntry.getValue().length];
            int i = 0;
            for (String s : stringEntry.getValue()) {
                String value = s;
                value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
                value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
                value = value.replaceAll("'", "& #39;");
                value = value.replaceAll("eval\\((.*)\\)", "");
                value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
                value = value.replaceAll("script", "");
                value = value.replaceAll("[*]","["+"*]");
                value = value.replaceAll("[+]","["+"+]");
                value = value.replaceAll("[?]","["+"?]");
                strings[i++]=value;
                String value1 =  s;

                for (String bad : badStrs) {
                    if (value1.equalsIgnoreCase(bad)) {
                        return  true;
                    }
                }
            }
            parameterMap.replace(stringEntry.getKey(),strings);
        }
        return false;
    }
}
