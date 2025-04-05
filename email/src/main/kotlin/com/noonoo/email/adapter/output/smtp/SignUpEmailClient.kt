package com.noonoo.email.adapter.output.smtp

import com.noonoo.email.domain.email.SignUpEmail
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.util.Base64
import org.springframework.core.io.ClassPathResource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils

@Component
class SignUpEmailClient(
    private val javaMailSender: JavaMailSender,
    private val freemarkerConfig: freemarker.template.Configuration
) {
    fun sendSignUpEmail(signUpEmail: SignUpEmail) {
        val imagePath = "assets/img/logo.avif"
        val imageBytes = Files.readAllBytes(ClassPathResource(imagePath).file.toPath())
        val base64Image = Base64.getEncoder().encodeToString(imageBytes)

        val data =
            mapOf(
                "logo" to "data:image/avif;base64,$base64Image",
                "url" to "http://localhost:8080/api/auth/signup/verify/${signUpEmail.token}",
                "name" to signUpEmail.name,
                "email" to signUpEmail.email
            )

        val mimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name())

        val template = freemarkerConfig.getTemplate("signup.ftl")
        val html = FreeMarkerTemplateUtils.processTemplateIntoString(template, data)

        helper.setTo(signUpEmail.email)
        helper.setSubject("Welcome to Noonoo")
        helper.setText(html, true)
        helper.setFrom("noonooTv@gmail.com")

        javaMailSender.send(mimeMessage)
    }
}
