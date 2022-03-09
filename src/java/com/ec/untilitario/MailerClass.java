/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.untilitario;


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.BodyPart;
import javax.mail.Transport;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeUtility;

/**
 * Clase que permite el envio de e-mails utilizando el API javamail.
 *
 */
public class MailerClass {
public String usuario;
	public String contrasenia;

	/**
	 * Recupera el nombre del catálogo descrito en la enumeración
	 *
	 * @param categoria nombre del parametroa a buscar
	 * @return
	 */
	public String getConfiguracionCorreo(String categoria) {

		return null;
	}

	class SmtpAuthenticator extends Authenticator {

		public SmtpAuthenticator() {

			super();
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			System.out.println("nombreUsaurio " + usuario);
			return new PasswordAuthentication(usuario, contrasenia);

		}
	}

	// envio de mail simple
	//
//	      m.setRecipients(Message.RecipientType.TO,
//	                    InternetAddress.parse(address));
	public boolean sendMailSimple(String urlPago, String name, String correodestino, String asunto) {

		try {
//	            this.usuario = nombreUsaurio;
//	            this.contrasenia = contrasenia;
//	            String asunto = asuntoP;
//	            String host = smtpHost;
//	            String port = puerto;
//	            String protocol = "smtp";
//	            String usuarioSmpt = nombreUsaurio;
//	            String password = contrasenia;

			String host = "mail.orienteseguros.com";
			String port = "465";
			String protocol = "smtp";
			String usuarioSmpt = "vinculacion@orienteseguros.com";
			String password = "Test.2030_";
			/* PARA LA AUTENTICACION */
			this.usuario = usuarioSmpt;
			this.contrasenia = password;

			// Propiedades de la conexión
			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);
			properties.setProperty("mail.smtp.user", usuarioSmpt);
			properties.setProperty("mail.smtp.password", password);
			properties.setProperty("mail.smtp.port", port);
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.debug", "false");
			// Setup Port
			properties.put("mail.smtp.ssl.trust", host);
			SmtpAuthenticator auth = new SmtpAuthenticator();
			// Get the default Session object.
			Session session = Session.getInstance(properties, auth);
			MimeMessage m = new MimeMessage(session);
			String nickFrom = MimeUtility.encodeText("Oriente Seguros");
			Address addressfrom = new InternetAddress(usuarioSmpt, nickFrom);
//	            Address addressfrom = new InternetAddress(usuarioSmpt);

			m.setFrom(addressfrom);

			BodyPart texto = new MimeBodyPart();
			String HTMLENVIO = "  \r\n" + "<html\r\n" + "	xmlns:v=\"urn:schemas-microsoft-com:vml\"\r\n"
					+ "	xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n"
					+ "	xmlns:w=\"urn:schemas-microsoft-com:office:word\"\r\n"
					+ "	xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"\r\n"
					+ "	xmlns=\"http://www.w3.org/TR/REC-html40\">\r\n" + "	<head>\r\n"
					+ "		<meta http-equiv=\"Content-Type\" content=\"text/html; \">\r\n"
					+ "			<meta name=\"Generator\" content=\"Microsoft Word 15 (filtered medium)\">\r\n"
					+ "				<!--[if !mso]>\r\n"
					+ "				<style>v\\:* {behavior:url(#default#VML);}  o\\:* {behavior:url(#default#VML);}  w\\:* {behavior:url(#default#VML);}  .shape {behavior:url(#default#VML);}  </style>\r\n"
					+ "				<![endif]-->\r\n" + "				<style>\r\n"
					+ "					<!--  /* Font Definitions */  @font-face  {font-family:\"Cambria Math\";  panose-1:2 4 5 3 5 4 6 3 2 4;}  @font-face  {font-family:Calibri;  panose-1:2 15 5 2 2 2 4 3 2 4;}  @font-face  {font-family:\"Open Sans\";}  /* Style Definitions */  p.MsoNormal, li.MsoNormal, div.MsoNormal  {margin:0cm;  margin-bottom:.0001pt;  font-size:12.0pt;  font-family:\"Times New Roman\",serif;}  a:link, span.MsoHyperlink  {mso-style-priority:99;  color:#0563C1;  text-decoration:underline;}  a:visited, span.MsoHyperlinkFollowed  {mso-style-priority:99;  color:#954F72;  text-decoration:underline;}  p  {mso-style-priority:99;  mso-margin-top-alt:auto;  margin-right:0cm;  mso-margin-bottom-alt:auto;  margin-left:0cm;  font-size:12.0pt;  font-family:\"Times New Roman\",serif;}  span.EstiloCorreo18  {mso-style-type:personal;  font-family:\"Calibri\",sans-serif;  color:#1F497D;}  span.EstiloCorreo19  {mso-style-type:personal-compose;  font-family:\"Calibri\",sans-serif;  color:windowtext;}  .MsoChpDefault  {mso-style-type:export-only;  font-family:\"Calibri\",sans-serif;  mso-fareast-language:EN-US;}  @page WordSection1  {size:612.0pt 792.0pt;  margin:70.85pt 3.0cm 70.85pt 3.0cm;}  div.WordSection1  {page:WordSection1;}  -->\r\n"
					+ "				</style>\r\n" + "				<!--[if gte mso 9]>\r\n"
					+ "				<xml>\r\n"
					+ "					<o:shapedefaults v:ext=\"edit\" spidmax=\"1026\" />\r\n"
					+ "				</xml>\r\n" + "				<![endif]-->\r\n"
					+ "				<!--[if gte mso 9]>\r\n" + "				<xml>\r\n"
					+ "					<o:shapelayout v:ext=\"edit\">\r\n"
					+ "						<o:idmap v:ext=\"edit\" data=\"1\" />\r\n"
					+ "					</o:shapelayout>\r\n" + "				</xml>\r\n"
					+ "				<![endif]-->\r\n" + "			</head>\r\n"
					+ "			<body lang=\"ES-AR\" link=\"#0563C1\" vlink=\"#954F72\" style='tab-interval:0pt'>\r\n"
					+ "				<div class='WordSection1'>\r\n"
					+ "					<table class='MsoTableGrid' border='0' cellspacing='0' cellpadding='0'   style='border-collapse:collapse;border:none;mso-border-alt:solid windowtext .5pt;   mso-yfti-tbllook:1536;mso-padding-alt:0cm 0pt 0cm 0pt'>\r\n"
					+ "						<tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;mso-yfti-lastrow:yes'>\r\n"
					+ "							<td width='600' valign='top' style='width:600px;border:none;background:#FFFFFF;padding:0cm 0pt 0cm 0pt'>\r\n"
					+ "								<p class='MsoNormal'>\r\n"
					+ "									<o:p>\r\n"
					+ "										<div id=\"primaryContainer\" class=\"primaryContainer clearfix\" style=\"height: auto; margin-right: auto; margin-right: auto; min-height: 100%; width: 100%; font-family: 'Roboto', 'Open Sans', sans-serif;\">\r\n"
					+ "											<div id=\"box\" class=\"clearfix\" style=\"background-color: #FFFFFF;\">\r\n"
					+ "												<img id=\"image\" style=\"float: left; width='590'; height='120';\" src=\"http://servicios.orienteseguros.com/app/resources/mail/img/header_btn_pago.jpg\" />\r\n"
					+ "												<p>\r\n"
					+ "													<!--<span id=\"textspan\" style=\"float: none; font-size: 19px; line-height: 1em; color: #651717; font-weight: 600;\">        Estimado: "
					+ name + ",      </span>-->\r\n"
					+ "													<span style=\"color:#726658;font-family:'Apex New Book';font-size:21px;line-height:57px;\">              Hola "
					+ name + ",             </span>\r\n"
					+ "													<br/>\r\n"
					+ "												</p>\r\n"
					+ "												<p>\r\n"
					+ "													<span  style=\"color:#726658;font-family:'Apex New Book';font-size:21px;line-height:57px;\">              Est&#225s a un clic de sumar a Oriente Seguros como tu socio estrat&#233gico.             </span>\r\n"
					+ "												</p>\r\n"
					+ "												<p>\r\n"
					+ "													<span style=\"color:#726658;font-family:'Apex New Book';font-size:21px;line-height:57px;\">              Ingresa en el siguiente enlace para realizar tu pago:                 \r\n"
					+ "														<br/>\r\n"
					+ "														<strong>\r\n"
					+ "															<a href=" + urlPago
					+ " style=\"color:#f58d42;\">Link de pago</a>           \r\n"
					+ "														</strong>\r\n"
					+ "													</span>\r\n"
					+ "													<br/>\r\n"
					+ "												</p>\r\n"
					+ "												<p>\r\n"
					+ "													<span style=\"color:#726658;font-family:'Apex New Book';font-size:21px;line-height:19px;\">Por favor comun&#237cate a </span>\r\n"
					+ "													<span style=\"color:#0000FF;font-family:'Apex New Book';font-size:21px;line-height:19px;\">\r\n"
					+ "														<strong>\r\n"
					+ "															<u>\r\n"
					+ "																<a href=\"mailto:oriente@orienteseguros.com\" style=\"color:#f58d42;\">oriente@orienteseguros.com</a>\r\n"
					+ "															</u>\r\n"
					+ "														</strong>\r\n"
					+ "													</span>\r\n"
					+ "													<span style=\"color:#726658;font-family:'Apex New Book';font-size:21px;line-height:19px;\">              o al \r\n"
					+ "														<strong>1800 ORIENTE</strong> en  caso de cualquier inquietud o requerimiento.             \r\n"
					+ "													</span>\r\n"
					+ "													<br/>\r\n"
					+ "												</p>\r\n"
					+ "												<p>\r\n"
					+ "													<span style=\"color:#726658;font-family:'Apex New Book';font-size:21px;line-height:48px;\">Estamos a tu lado mientras alcanzas tus metas y objetivos. </span>\r\n"
					+ "													<br/>\r\n"
					+ "												</p>\r\n"
					+ "												<p>\r\n"
					+ "													<span style=\"color:#726658;font-family:'Apex New Book';font-size:21px;line-height:19px;\">Oriente Seguros, seguros de lo que somos. </span>\r\n"
					+ "													<br/>\r\n"
					+ "													<br/>\r\n"
					+ "												</p>\r\n"
					+ "											</div>\r\n"
					+ "											<footer>\r\n"
					+ "												<img id=\"image1\" class=\"image\" style=\"float: left; width='590'; height='95';\" src=\"http://servicios.orienteseguros.com/app/resources/mail/img/foot_btn_pago.jpg\" />\r\n"
					+ "											</footer>\r\n"
					+ "										</div>\r\n" + "									</o:p>\r\n"
					+ "								</p>\r\n" + "							</td>\r\n"
					+ "						</tr>\r\n" + "					</table>\r\n" + "				</div>\r\n"
					+ "			</body>\r\n" + "		</html>";
			texto.setContent(HTMLENVIO, "text/html");

			MimeMultipart multiParte = new MimeMultipart();
			// inicio adjunto
//	            if (attachFiles != null && attachFiles.length > 0) {
//	                for (String filePath : attachFiles) {
//	                    MimeBodyPart attachPartDoc = new MimeBodyPart();
//	                    try {
//	                        if (!filePath.equals("")) {
//	                            attachPartDoc.attachFile(filePath);
//	                        }
//	                    } catch (IOException ex) {
//	                        ex.printStackTrace();
//	                    }
//	                    multiParte.addBodyPart(attachPartDoc);
//	                }
//	            }
			m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correodestino));
			multiParte.addBodyPart(texto);

//	            m.setRecipients(Message.RecipientType.TO, addresTto);
//	            m.setRecipients(Message.RecipientType.BCC, from);
			m.setSubject(asunto);
			m.setSentDate(new java.util.Date());
//	             m.setContent(dirDatos, "text/plain");
			m.setContent(multiParte);

			Transport t = session.getTransport(protocol);
//	             t.connect();
			t.connect(host, usuarioSmpt, password);
			t.send(m);
			t.close();
			return true;
		} catch (javax.mail.MessagingException e) {
			System.out.println("error" + e);

			return false;
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(MailerClass.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
}
