package sprout.BusRide.service;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;

@Component
public class XMLtoJSON {

    public String XMLtoJson(String xml) {
        try {
            // XML 문자열
            String xmlString = xml;
            // XML 파싱
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));

            // XML 루트 요소 가져오기
            Element rootElement = document.getDocumentElement();

            // msgBody 요소 추출
            Element msgBodyElement = (Element) rootElement.getElementsByTagName("msgBody").item(0);

            // itemList 요소들 추출
            NodeList itemListNodes = msgBodyElement.getElementsByTagName("itemList");

            // itemList를 JSONArray로 처리
            JSONArray itemListArray = new JSONArray();
            for (int i = 0; i < itemListNodes.getLength(); i++) {
                Element itemListElement = (Element) itemListNodes.item(i);

                // itemList 요소의 자식 노드들을 처리하여 JSONObject로 생성
                JSONObject itemObject = new JSONObject();
                NodeList childNodes = itemListElement.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);
                        String tagName = childElement.getTagName();
                        String textContent = childElement.getTextContent();
                        itemObject.put(tagName, textContent);
                    }
                }

                // itemList JSONObject를 JSONArray에 추가
                itemListArray.put(itemObject);
            }

            // 최종 결과 JSON 객체 생성
            JSONObject resultJson = new JSONObject();
            resultJson.put("itemList", itemListArray);

            return resultJson.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
