package com.wise

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client.{Admin, Connection, ConnectionFactory, Put, Table}
import org.apache.hadoop.hbase.util.Bytes

object HBaseConnector {
  val config: Configuration = HBaseConfiguration.create
  val connection: Connection = ConnectionFactory.createConnection(config)
  val admin: Admin = connection.getAdmin

  abstract class TableProps{
    def name: String
    def cfs: Map[String, Array[Byte]]
    def columns: Map[String, Array[Byte]]
  }

  case class ContaTable(
                         name: String = "conta",
                         cfs: Map[String, Array[Byte]] = Map(
                           ("cfConta", Bytes.toBytes("conta_data"))
                         ),
                         columns: Map[String, Array[Byte]] = Map(
                           ("json", Bytes.toBytes("json"))
                         )
                       ) extends TableProps

  val contaTable = ContaTable()

  // Get table if exists, else create table
  def getOrCreateTable(table: TableProps): Table = {
    val tableName = TableName.valueOf(table.name)
    if (!admin.tableExists(tableName)) {
      println(s"Creating ${table.name} Table")
      val tableDesc = new HTableDescriptor(tableName)
      table.cfs.foreach(cf => tableDesc.addFamily(new HColumnDescriptor(cf._2)))
      admin.createTable(tableDesc)
    }
    connection.getTable(tableName)
  }

  // Converting cache rdd row to Put object
  def convertToPutCache(key: String, value: String): Put = {
    val put = new Put(Bytes.toBytes(key))
    put.addColumn(contaTable.cfs("cfConta"), contaTable.columns("json"), Bytes.toBytes(value))
    put
  }

}
