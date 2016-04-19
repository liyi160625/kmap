package test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.andy.kmap.model.entity.Academy;
import org.andy.kmap.model.entity.DropDownModel.CourseDropDown;
import org.andy.kmap.model.entity.DropDownModel.GradeDropDown;
import org.andy.kmap.model.service.apiService.APIAcademyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;
import javax.swing.tree.TreeNode;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Mtime on 2015/9/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc.xml")
public class test {

//    @Autowired
//    @Qualifier("APIAcademyService")
//    private APIAcademyService apiAcademyService;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(test.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    public void sendMailTest(){

        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);
            messageHelper.setFrom("Echo_rejoice@163.com");
            messageHelper.setTo("1126323106@qq.com");

            messageHelper.setSubject("测试邮件中嵌套图片!！");
            // true 表示启动HTML格式的邮件
            messageHelper.setText(
                    "<html><head></head><body><h1>hello!!spring image html mail</h1>"
                            +"<a href=http://www.baidu.com>baidu</a>"+ "<img src=cid:image/>"
                    +"<img src=cid:test/></body></html>", true);

            FileSystemResource img = new FileSystemResource(new File("/Users/apple/desktop/IMG_0735.jpg"));
            messageHelper.addInline("image", img);//跟cid一致
            FileSystemResource image2 = new FileSystemResource(new File("/Users/apple/desktop/IMG_0013.jpg"));
            messageHelper.addInline("test",image2);

            javaMailSender.send(mailMessage);
            System.out.println("邮件发送成功...");

        }catch (Exception ex){

        }

    }

    @Test
    public void sqlTest(){
        final List<GradeDropDown> gradeDropDowns=new ArrayList<GradeDropDown>();

        String sqlForGrades="select year from major where name='电子商务'";

        jdbcTemplate.query(sqlForGrades,  new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

                GradeDropDown gradeDropDown=new GradeDropDown();
                logger.info("获取下拉列表年级为"+resultSet.getInt(1));
                gradeDropDown.setText(String.valueOf(resultSet.getInt("year")));
                gradeDropDown.setHref("#grandchild1");
                gradeDropDown.setTags(null);
                //添加知识点的下拉列表
                gradeDropDowns.add(gradeDropDown);

            }
        });

        logger.info("获取下拉列表,年级:"+new Gson().toJson(gradeDropDowns));
    }
}
