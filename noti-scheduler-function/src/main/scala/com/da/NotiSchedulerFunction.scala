package com.da

import java.util.logging.Level

import com.da.AzureStorageAccount.{Environment, _}
import com.da.EnvironmentService._
import com.microsoft.azure.serverless.functions.ExecutionContext
import com.microsoft.azure.serverless.functions.annotation.{FunctionName, TimerTrigger}
import com.microsoft.azure.storage.queue.{CloudQueue, CloudQueueMessage}

/**
  * Created by williame on 11/3/17.
  */
class NotiSchedulerFunction {

  @FunctionName("danotischeduler")
  def sendNotification(@TimerTrigger(name = "DANotiScheduler", dataType = "String", schedule = "0 */30 * * * *") tick: String, ec: ExecutionContext) = {
    val environment = getEnvironment(System.getenv())
    val queue = getQueue(environment)
    val message = Option(queue.retrieveMessage())
    trySendMessage(ec, environment, queue, message)
  }

  private def trySendMessage(ec: ExecutionContext, environment: Environment, queue: CloudQueue, message: Option[CloudQueueMessage]) = {
    message match {
      case Some(m) =>
        sendNotificationMessage(m.getMessageContentAsString, environment, ec)
        queue.deleteMessage(m)
      case None =>
        ec.getLogger.log(Level.INFO, "nothing to send")
    }
  }

  def sendNotificationMessage(message: String, environment: Environment, ec: ExecutionContext) = {
    ec.getLogger.log(Level.INFO, message)
  }

}
