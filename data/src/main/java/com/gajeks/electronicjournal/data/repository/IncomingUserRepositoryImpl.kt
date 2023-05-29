package com.gajeks.electronicjournal.data.repository

import com.gajeks.electronicjournal.data.models.SenderEmailAccount
import com.gajeks.electronicjournal.domain.repository.IncomingUserRepository
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class IncomingUserRepositoryImpl : IncomingUserRepository {

    override suspend fun sendEmailWithCode(code: Int, emailReceiver: String) {
        val properties = System.getProperties()
        properties["mail.smtp.host"] = SenderEmailAccount.gmailHost
        properties["mail.smtp.port"] = "465"
        properties["mail.smtp.ssl.enable"] = "true"
        properties["mail.smtp.auth"] = "true"

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(
                    SenderEmailAccount.emailAddress,
                    SenderEmailAccount.emailPassword
                )
            }
        })

        val message = MimeMessage(session)
        try {
            message.addRecipient(Message.RecipientType.TO, InternetAddress(emailReceiver))
            message.subject = "Security Code"
            message.setText("Your security code for changing password: $code")
            Transport.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
            throw RuntimeException()
        }
    }

}