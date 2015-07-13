package com.hannuus.gamble.comm;

import java.text.MessageFormat;

public class PaginationUtils {
	
    public static String getPageString(int pageSize, int pageIndex, int count, String type)
    {
        int totalPageSize = 0; //总页数
        int mo = count % pageSize;
        if (mo == 0)
            totalPageSize = count / pageSize;
        else
            totalPageSize = count / pageSize + 1;
        StringBuilder table = new StringBuilder();
        table.append("<div style=\" margin:auto;width:500px;\" >");
        table.append("<table class=\"table\" cellspacing=\"2\"> \n");
        table.append("<tr>\n");
        
        table.append(MessageFormat.format("<td class=\"row\"><a href=\"javascript:void(0)\">每页{0}条</a></td>\n", pageSize));
        table.append(MessageFormat.format("<td class=\"row\"><a href=\"javascript:void(0)\">共{0}条</a></td>\n", count));
        table.append(MessageFormat.format("<td class=\"row\"><a href=\"javascript:void(0)\">{0}/{1}</a></td>\n", pageIndex, totalPageSize));
        table.append(MessageFormat.format("<td class=\"row\"><a href=\"javascript:void(0)\">共{0}页</a></td>\n", totalPageSize));
        if (pageIndex == 1)
        {
            table.append("<td class=\"row\"><a href=\"javascript:void(0)\">首页</a></td>\n");
            table.append("<td class=\"row\"><a href=\"javascript:void(0)\">上一页</a></td>\n");
        }
        else
        {
        	table.append("<td class=\"row\"><a href=\"javascript:" + type + "(1)\">首页</a></td>\n");
        	table.append(MessageFormat.format("<td class=\"row\"><a href=\"javascript:" + type + "({0})\">上一页</a></td>\n", pageIndex - 1));
        }
        if (pageIndex == totalPageSize)
        {
            table.append("<td class=\"row\"><a href=\"javascript:void(0)\">下一页</a></td>\n");
            table.append("<td class=\"row\"><a href=\"javascript::void(0)\">尾页</a></td>\n");
        }
        else
        {
        	table.append(MessageFormat.format("<td class=\"row\"><a href=\"javascript:" + type + "({0})\">下一页</a></td>\n", pageIndex + 1));
        	table.append(MessageFormat.format("<td class=\"row\"><a href=\"javascript:" + type + "({0})\">尾页</a></td>\n", totalPageSize));
        }
        table.append("</tr>\n");
        table.append("</table> \n");

        table.append("</div>");
        return table.toString();

    }
}
