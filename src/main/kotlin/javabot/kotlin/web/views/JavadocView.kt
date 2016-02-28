package javabot.kotlin.web.views

import com.google.inject.Inject
import com.google.inject.assistedinject.Assisted
import javabot.dao.AdminDao
import javabot.dao.ApiDao
import javabot.dao.ChannelDao
import javabot.dao.FactoidDao
import javabot.javadoc.JavadocApi
import javax.servlet.http.HttpServletRequest

class JavadocView @Inject constructor(
        adminDao: AdminDao,
        channelDao: ChannelDao,
        factoidDao: FactoidDao,
        var apiDao: ApiDao, @Assisted request: HttpServletRequest) :
        MainView(adminDao, channelDao, factoidDao, request) {

    override fun getChildView(): String {
        return "admin/javadoc.ftl"
    }

    fun apis(): List<JavadocApi> {
        return apiDao.findAll()
    }
}
