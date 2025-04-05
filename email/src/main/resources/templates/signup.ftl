<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body style="margin:0; padding:0; background-color:#ffffff; font-family:Arial, sans-serif;">
<table width="100%" cellpadding="0" cellspacing="0" border="0" style="background-color:#ffffff;">
    <tr>
        <td align="center">
            <table width="600" cellpadding="0" cellspacing="0" border="0" style="margin:0 auto;">
                <!-- Header -->
                <tr>
                    <td align="center" style="padding: 20px;">
                        <img src="${logo}" alt="누누티비" style="width:145px; max-width:100%; display:block;" />
                    </td>
                </tr>

                <!-- Title and greeting -->
                <tr>
                    <td style="padding: 0 20px;">
                        <h1 style="font-size: 24px; margin-top:14px; color:#000000;">누누티비에 가입해 주셔서 감사합니다.</h1>
                        <p style="font-size: 16px; margin-top:14px; color:#000000;">${name} 님, 안녕하세요.</p>
                        <p style="font-size: 16px; margin-top:14px; color:#000000;">이메일 인증 후, TV 프로그램과 영화를 마음껏 시청하실 수 있습니다.</p>
                    </td>
                </tr>

                <tr>
                    <td style="padding: 20px;">
                        <a href="${url}" style="display:inline-block; padding: 12px 24px; font-size:16px; color:#ffffff; background-color:#e50914; text-decoration:none; border-radius:2px;">
                            인증하러 가기
                        </a>
                    </td>
                </tr>

                <tr>
                    <td style="padding: 0 20px;">
                        <h4 style="margin-top:30px; margin-bottom: 20px; font-size:18px; color:#000000;">계정 정보</h4>
                        <p style="margin:5px 0; font-size:14px; color:#696666;">이메일</p>
                        <p style="margin:0 0 12px 0; font-size:16px; color:#000000;">${email}</p>
                        <p style="margin:4px 0; font-size:14px; color:#696666;">이름</p>
                        <p style="margin:0 0 12px 0; font-size:16px; color:#000000;">${name}</p>
                        <p style="font-size:16px; margin-top: 14px; color:#000000;">질문이 있거나 도움이 필요한 경우, 고객 센터를 방문하거나 직접 문의해주세요.</p>
                        <p style="font-size:16px; margin-top: 14px; margin-bottom: 30px; color:#000000;">누누 드림</p>
                    </td>
                </tr>

                <!-- Footer -->
                <tr>
                    <td style="padding: 20px; border-top: 3px solid #e50914;">
                        <p style="font-size:14px; font-weight:bold; color:#e50914;">TV 프로그램과 영화 무엇이든 고르세요</p>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
