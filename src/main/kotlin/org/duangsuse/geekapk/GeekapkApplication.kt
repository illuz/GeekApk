package org.duangsuse.geekapk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import java.io.BufferedInputStream
import java.io.IOException
import java.util.*

@ServletComponentScan
@SpringBootApplication
class GeekapkApplication

fun main(args: Array<String>) {
  println(":: Starting GeekApk Spring server @ ${Date()}")

  val ini = GeekapkApplication::class.java.getResource("/info.ini")

  val file = ini.openStream().let(::BufferedInputStream)
  val buffer = ByteArray(file.available())

  try { file.read(buffer) }
  catch (ioe : IOException) { println("==! Failed to read initialization INI.") }

  // load defaults
  parseGeekINIBuffer(buffer)

  // load user config
  arrayOf("/geekapk.ini", "/translations.ini").forEach {
    println("=== Processing external properties file $it ===")
    val configFile = GeekapkApplication::class.java.getResource(it)
    parseGeekINIBuffer(configFile.openStream().let(::BufferedInputStream).readBytes())
  }

  println(":: Bootstrap SpringBoot Application ${GeekapkApplication::class}")

  val spring = runApplication<GeekapkApplication>(*args)

  spring.setId("GeekApk @ ${Thread.currentThread()}")
  spring.registerShutdownHook()
}

/**
 * Simple UNIX-style naive text processing util with unbelievable time complexity
 *
 * Sets system properties
 *
 * @author duangsuse
 */
private fun parseGeekINIBuffer(buffer: ByteArray) {
  var tag = ""

  String(buffer).lines().forEach setProp@{
    println("==> $it")

    if (it.trimStart().startsWith('#') or it.isBlank())
      return@setProp

    if (it.startsWith('[') && it.endsWith(']')) {
      tag = it.substring(1 until it.lastIndex)
      println("== Configure section [$tag]")
      return@setProp
    }

    val pair = it.split('=')

    if (pair.size < 2) {
      println("==! Illegal set ${pair.toList()}")
      return@setProp
    }

    val (key, value) = (pair[0] to pair[1])
    val realPre = if (tag.isEmpty()) "" else tag.plus(".")
    System.setProperty("$realPre$key", value)

    println("== Setup $tag($key) to ($value)")
  }
}
