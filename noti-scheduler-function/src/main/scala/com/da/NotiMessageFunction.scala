package com.da

import com.da.AzureStorageAccount._
import com.da.EnvironmentService._
import com.microsoft.azure.serverless.functions.ExecutionContext
import com.microsoft.azure.serverless.functions.annotation.{AuthorizationLevel, FunctionName, HttpTrigger}
import com.microsoft.azure.storage.queue.CloudQueueMessage

/**
  * Created by williame on 11/3/17.
  */
class NotiMessageFunction {

  @FunctionName("danotiservice")
  def addMessage(@HttpTrigger(name = "req", methods = Array("get", "post"), authLevel = AuthorizationLevel.ANONYMOUS) req: String, ec: ExecutionContext): String = {
    val environment = getEnvironment(System.getenv())
    val queue = getQueue(environment)
    queue.addMessage(new CloudQueueMessage(req))
    "ok"
  }
}
