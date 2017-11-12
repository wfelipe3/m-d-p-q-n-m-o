package com.da

import com.microsoft.azure.storage.CloudStorageAccount

/**
  * Created by williame on 11/3/17.
  */
object AzureStorageAccount {

  case class Environment(saConnectionString: String, appInsights: String, queue: String)

  def getQueue(environment: Environment) = {
    val storageAccount = CloudStorageAccount.parse(environment.saConnectionString)
    val queueClient = storageAccount.createCloudQueueClient()
    queueClient.getQueueReference(environment.queue)
  }

}
