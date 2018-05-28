package com.basic.core.admin.controller.system;

import com.basic.core.admin.constant.SystemConstant;
import com.basic.core.admin.shiro.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: wangzw
 * @Date: 2017/4/1 10:42
 * @Description: 生成验证码
 * @Version: 1.0
 */
@Controller
public class VerifyCodeController {

    private final int verifyCodeLength=4;

    @RequestMapping("/verifyCode.do")
    public void generate(HttpServletResponse response){
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            String verifyCode = drawImg(output);
            ShiroUtils.setSessionAttribute(SystemConstant.SYS_CURRENT_LOGINUSER_VERIFYCODE,verifyCode);
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String drawImg(ByteArrayOutputStream output){
        StringBuilder codeBuilder = new StringBuilder();
        for(int i=0; i<verifyCodeLength; i++){
            codeBuilder.append(randomChar());
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman",Font.PLAIN,20);
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66,2,82);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(codeBuilder.toString(), context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(codeBuilder.toString(), (int)x, (int)baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeBuilder.toString();
    }

    private char randomChar(){
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }

}
