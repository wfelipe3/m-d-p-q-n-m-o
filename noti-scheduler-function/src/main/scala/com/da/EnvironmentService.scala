package com.da

import com.da.AzureStorageAccount.Environment
import java.util

/**
  * Created by williame on 11/3/17.
  */
object EnvironmentService {

  def getEnvironment(env: util.Map[String, String]): Environment = {
    val sa = env.get("AzureWebJobsStorage")
    val appIns = env.get("APPINSIGHTS_INSTRUMENTATIONKEY")
    val queue = env.get("NotiQueue")
    Environment(sa, appIns, queue)
  }
}
