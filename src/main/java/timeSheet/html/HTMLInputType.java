package timeSheet.html;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * User: John Lawrence
 * Date: 3/26/11
 * Time: 2:17 PM
 */
public enum HTMLInputType {
    @XmlEnumValue("button") button,
    @XmlEnumValue("checkbox") checkbox,
    @XmlEnumValue("color") color,
    @XmlEnumValue("date") date,
    @XmlEnumValue("datetime") dateTime,
    @XmlEnumValue("datetime-local") dateTimeLocal,
    @XmlEnumValue("email") email,
    @XmlEnumValue("file") file,
    @XmlEnumValue("hidden") hidden,
    @XmlEnumValue("image") image,
    @XmlEnumValue("month") month,
    @XmlEnumValue("number") number,
    @XmlEnumValue("password") password,
    @XmlEnumValue("radio") radio,
    @XmlEnumValue("range") range,
    @XmlEnumValue("reset") reset,
    @XmlEnumValue("search") search,
    @XmlEnumValue("submit") submit,
    @XmlEnumValue("tel") tel,
    @XmlEnumValue("text") text,
    @XmlEnumValue("time") time,
    @XmlEnumValue("url") url,
    @XmlEnumValue("week") week,
}
