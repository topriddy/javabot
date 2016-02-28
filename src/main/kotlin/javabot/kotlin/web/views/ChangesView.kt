package javabot.kotlin.web.views

import com.google.inject.assistedinject.Assisted
import javabot.dao.AdminDao
import javabot.dao.ChangeDao
import javabot.dao.ChannelDao
import javabot.dao.FactoidDao
import javabot.dao.util.QueryParam
import javabot.model.Change
import java.time.LocalDateTime
import javax.annotation.Nullable
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

class ChangesView @Inject constructor(adminDao: AdminDao,
                                      channelDao: ChannelDao,
                                      factoidDao: FactoidDao,
                                      var changeDao: ChangeDao,
                                      @Assisted request: HttpServletRequest,
                                      @Assisted page: Int,
                                      @Nullable @Assisted private val message: String?,
                                      @Nullable @Assisted private val date: LocalDateTime?) :
      PagedView<Change>(adminDao, channelDao, factoidDao, request, page) {

    override fun getPagedView(): String {
        return "changes.ftl"
    }

    override fun countItems(): Long {
        return changeDao.count(message, date)
    }

    override fun getPageUrl(): String {
        return "/changes"
    }

    override fun getPageItems(): List<Change> {
        return changeDao.getChanges(QueryParam(getIndex(), PagedView.ITEMS_PER_PAGE, "updated", true), message, date)
    }
}
