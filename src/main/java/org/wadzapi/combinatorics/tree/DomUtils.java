package org.wadzapi.combinatorics.tree;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.FormattedMessage;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Утилитные методы для работы с DOM-деревом
 */
public class DomUtils {

    /**
     * Кодировка по умолчания для xml-ресурсов
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Признак добавления отступов для элементов
     */
    private static final boolean IS_INDENT_ELEMENTS = true;

    /**
     * Логгер
     */
    private static final Logger LOGGER = LogManager.getLogger(DomUtils.class);

    /**
     * Конструктор класса
     */
    private DomUtils() {
    }

    /**
     * Метод чтения DOM-документа из ресурса
     * @param resourceName название ресурса
     * @return DOM-документ
     * @throws DomException ошибка чтения DOM
     */
    public static Document readResource(String resourceName) throws DomException {
        final String logMsg = "{} чтения DOM мз ресурса";
        LOGGER.debug(logMsg, "Начало");
        try(InputStream xmlStream = DomUtils.class.getClassLoader().getResourceAsStream(resourceName)) {
            Document document = readInputStream(xmlStream);
            LOGGER.debug(logMsg, "Конец");
            return document;
        } catch (IOException e) {
            final String errMsg = new FormattedMessage(logMsg, "Ошибка").getFormattedMessage();
            throw new DomException(errMsg, e);
        }
    }

    /**
     * Метод чтения DOM из входного потока данных
     * @param inputStream входной поток данных
     * @return DOM-документ
     * @throws DomException ошибка чтения DOM
     */
    private static Document readInputStream(InputStream inputStream) throws DomException {
        final String logMsg = "{} чтения DOM из входного потока данных";
        LOGGER.debug(logMsg, "Начало");
        InputSource inputSource = new InputSource(inputStream);
        Document document = readInputSource(inputSource);
        LOGGER.debug(logMsg, "Начало");
        return document;
    }

    /**
     * Метод чтения DOM из inputSource
     * @param source inputSource
     * @return DOM-документ
     * @throws DomException ошибка чтения DOM
     */
    private static Document readInputSource(InputSource source) throws DomException {
        final String logMsg = "{} чтения DOM из inputSource";
        LOGGER.debug(logMsg, "Начало");
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(source);
            LOGGER.debug(logMsg, "Конец");
            return document;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            final String errMsg = new FormattedMessage(logMsg, "Ошибка").getFormattedMessage();
            throw new DomException(errMsg, e);
        }
    }

    /**
     * Метод преобразования поддерева в строковое представление
     * @param element элемент
     * @return строковое представление поддерева
     * @throws DomException ошибка чтения DOM
     */
    public static String toString(Element element) throws DomException {
        final String logMsg = "{} преобразования поддрева в строковое представление";
        LOGGER.debug(logMsg, "Начало");
        try {
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setParameter(OutputKeys.ENCODING, DEFAULT_ENCODING);
            transformer.setParameter(OutputKeys.INDENT, IS_INDENT_ELEMENTS);
            StringWriter resultWriter = new StringWriter();
            transformer.transform(new DOMSource(element), new StreamResult(resultWriter));
            LOGGER.debug(logMsg, "Конец");
            return resultWriter.toString();
        } catch (TransformerException e) {
            final String errMsg = new FormattedMessage(logMsg, "Ошибка").getFormattedMessage();
            throw new DomException(errMsg, e);
        }
    }
}