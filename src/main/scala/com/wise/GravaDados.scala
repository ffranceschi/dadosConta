package com.wise

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client.{Admin, Connection, ConnectionFactory, Put, Table}
import org.apache.hadoop.hbase.util.Bytes

object GravaDados {

  case class Dados (key: String, value: String)

  def main(args: Array[String]): Unit = {
    val conf : Configuration = HBaseConfiguration.create
    val ZOOKEEPER_QUORUM = "localhost:2181"
    conf.set("hbase.zookeeper.quorum", ZOOKEEPER_QUORUM)
    val connection = ConnectionFactory.createConnection(conf)
    val table = connection.getTable(TableName.valueOf( Bytes.toBytes("conta") ) )

    // Put example
    var put = new Put(Bytes.toBytes("row1"))
    put.addColumn(Bytes.toBytes("d"), Bytes.toBytes("valor"), Bytes.toBytes("40"))
    table.put(put)

    // Get example
//    println("Get Example:")
//    var get = new Get(Bytes.toBytes("row1"))
//    var result = table.get(get)
//    printRow(result)

    //Scan example
//    println("\nScan Example:")
//    var scan = table.getScanner(new Scan())
//    scan.asScala.foreach(result => {
//      printRow(result)
//    })

    table.close()
    connection.close()

  }

}
