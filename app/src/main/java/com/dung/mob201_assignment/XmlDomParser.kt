package com.dung.mob201_assignment

import android.util.Log
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import java.io.IOException
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

class XmlDomParser {

    fun getDocument(xml: String): Document{
        var document: Document? = null

        var factory = DocumentBuilderFactory.newInstance()
        try {
            var documentBuilder = factory.newDocumentBuilder()
            var inputSource = InputSource()
            inputSource.characterStream = StringReader(xml)
            inputSource.encoding = "UTF-8"
            document = documentBuilder.parse(inputSource)

        }catch (e: ParserConfigurationException){
            Log.e("Error: ",e.toString())
        }
        catch (e: SAXException){
            Log.e("Error: ",e.toString())
        }
        catch (e: IOException){
            Log.e("Error: ",e.toString())
        }

        return document!!
    }

    fun getValue(item: Element, name: String): String{
        var nodeList: NodeList = item.getElementsByTagName(name)
        return getNodeValue(nodeList.item(0))
    }

    fun getNodeValue(elem: Node): String {
        var child: Node?
        if (elem != null) {
            if (elem.hasChildNodes()) {
                child = elem.firstChild
                while (child != null) {
                    if (child.nodeType == Node.TEXT_NODE) {
                        return child.nodeValue
                    }
                    child = child.nextSibling
                }
            }
        }
        return ""
    }
}