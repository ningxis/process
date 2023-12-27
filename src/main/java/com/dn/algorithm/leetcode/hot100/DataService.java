package com.dn.algorithm.leetcode.hot100;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author dingning
 * @date 2023/12/10 下午 04:31
 **/
public class DataService {


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        test03();

    }

    public static void dataPost() throws IOException {
        String dataText = "姓名:\t吴雪蓉\t影像号:\t1954798\t诊断:\t\"\n" +
                "1、右肺术后，术区见条状致密影，周围可见少许条片状影，较前片相比无明显变化。\n" +
                "2、双下肺野少许渗出、实变病灶。\n" +
                "3、心影未见明显特殊。\n" +
                "4、双侧胸膜腔内少量积液。\"\t备注:\t26岁\t女\t检查时间:\t2023/9/15 12:53\n" +
                "asdfs\t姓名:\t韩叶桐\t影像号:\t1951445\t诊断:\t\"1.双肺纹理增多、模糊。\n" +
                "2.左肺肺门区链状致密影，周围少许条片状高密度影。左侧胸腔引流管影。左侧少量气胸。\n" +
                "3.心影不大。\n" +
                "4.右上纵隔区置管影。\"\t备注:\t23岁\t女\t检查时间:\t2023/9/15 12:55\n" +
                "asdfs\t姓名:\t段卫星\t影像号:\t1957530\t诊断:\t\"1.腰、骶尾椎骨质未见特殊X线征象。\n" +
                "2.腰椎过伸过屈未见明显失稳、滑脱表现。\"\t备注:\t33岁\t男\t检查时间:\t2023/9/15 12:57\n" +
                "asdfs\t姓名:\t李晓芳\t影像号:\t1950405\t诊断:\t\"1.右肺下野链状致密影，周围条片状高密度影。右侧胸腔引流管留置。右侧胸壁少许积气。\n" +
                "2.心影稍大。\n" +
                "3.左侧肋膈角显示欠清。\"\t备注:\t57岁\t女\t检查时间:\t2023/9/15 12:58\n" +
                "asdfs\t姓名:\t徐华\t影像号:\t1950996\t诊断:\t\"1.左肺链状致密影，周围条状高密度影。\n" +
                "2.双肺纹理增多、模糊，右肺下野条状高密度影。\n" +
                "3.心影不大。\"\t备注:\t61岁\t女\t检查时间:\t2023/9/15 12:59\n" +
                "asdfs\t姓名:\t曹旭阳\t影像号:\t1955639\t诊断:\t\"1、左上胸腔可见肺压缩带，考虑为气胸，肺叶压缩约30%。\n" +
                "2、肺纹理增多、紊乱。\n" +
                "3、左肺可见斑片状渗出灶，左肺上野见链状致密影。\n" +
                "4、左侧胸壁可见引流管置管影。\n" +
                "5、心脏不大。\n" +
                "6、双侧膈角锐利。\"\t备注:\t24岁\t女\t检查时间:\t2023/9/15 13:01\n" +
                "asdfs\t姓名:\t常开雄\t影像号:\t1953253\t诊断:\t\"1.左肺下野链状致密影，周围片状高密度影。\n" +
                "2.双肺纹理增多、模糊，双肺下野高密度影。\n" +
                "3.心影增大。\n" +
                "4.右上纵隔区置管影。\"\t备注:\t38岁\t男\t检查时间:\t2023/9/15 13:03\n" +
                "asdfs\t姓名:\t付平安\t影像号:\t1201033\t诊断:\t\"\n" +
                "1、右肺中下野及左肺上野见链条状致密影，周围小片稍高密度影，右侧胸部见引流管影，右侧胸壁软组织少许积气，考虑术后改变，请结合临床。\n" +
                "2、双肺散在渗出灶。\n" +
                "3、心外形稍大。\n" +
                "\n" +
                "对比9-14片未见明显变化。\"\t备注:\t49岁\t男\t检查时间:\t2023/9/15 13:04\n" +
                "asdfs\t姓名:\t田文成\t影像号:\t1936165\t诊断:\t\"\n" +
                "1.双肺纹理增多、模糊。\n" +
                "2.双肺下野片状高密度影。\n" +
                "3.心影不大，主动脉壁钙化。\n" +
                "4.左侧肋膈角显示不清。右侧胸壁积气。\"\t备注:\t61岁\t男\t检查时间:\t2023/9/15 13:07\n" +
                "asdfs\t姓名:\t石俊楠\t影像号:\t1935029\t诊断:\t\"1、左肺多发斑片、片状密度增高影，左侧肺野透亮度稍减低，较前变化不大，多考虑为感染，建议治疗后复查；左侧胸壁、左颈根部皮下气肿较前稍有减少。\n" +
                "2、左侧胸腔少量积液；现片左侧气胸征象不明确。\n" +
                "3、心影未见明显特殊。\"\t备注:\t22岁\t男\t检查时间:\t2023/9/15 13:08\n" +
                "asdfs\t姓名:\t李文甲\t影像号:\t1951655\t诊断:\t\"1、双肺纹理增多、紊乱。\n" +
                "2、双肺野见斑片状渗出、实变病灶。\n" +
                "3、心影不大；上纵隔影未见增宽。\n" +
                "4、双侧胸膜腔未见积液征象。  \"\t备注:\t67岁\t男\t检查时间:\t2023/9/15 13:10\n" +
                "asdfs\t姓名:\t杨天云\t影像号:\t1851335\t诊断:\t\"1、双肺纹理增多、紊乱。\n" +
                "2、双肺野见斑片状渗出、实变病灶。\n" +
                "3、心影增大，主动脉弓壁钙化；上纵隔影未见增宽。\n" +
                "4、双侧胸膜腔未见积液征象；左侧胸壁置管影。  \"\t备注:\t62岁\t男\t检查时间:\t2023/9/15 13:12\n" +
                "asdfs\t姓名:\t罗菊芬\t影像号:\t1943339\t诊断:\t\"1、左侧少许气胸。\n" +
                "2、双肺野见斑片状渗出。\n" +
                "3、心影稍增大。\n" +
                "4、左侧胸壁、右侧纵隔内见多根管影。\n" +
                "5、双侧肋膈角显示欠清。\"\t备注:\t47岁\t女\t检查时间:\t2023/9/15 13:17\n" +
                "asdfs\t姓名:\t吴少能\t影像号:\t1952157\t诊断:\t\"1、双肺弥漫片状高密度影，较前变化不大。\n" +
                "2、右肺下野内带可见类圆形高密度影，密度较前减低。\n" +
                "3、心影增大，主动脉弓钙化影。\n" +
                "4、双侧肋膈角显示欠清。右侧胸腔见置管影。\n" +
                "5、双侧颈根部、胸壁大量皮下积气，较前片无明显变化。\"\t备注:\t56岁\t男\t检查时间:\t2023/9/15 13:19\n" +
                "asdfs\t姓名:\t张玉华\t影像号:\t1953077\t诊断:\t\"1.双肺纹理增多、模糊，左肺下野小片状高密度影。右侧胸腔引流管留置，右侧胸壁少量积气。\n" +
                "2.心影增大。\n" +
                "3.上纵隔稍增宽。\"\t备注:\t60岁\t男\t检查时间:\t2023/9/15 13:20\n" +
                "asdfs\t姓名:\t刘翠琼\t影像号:\t1952377\t诊断:\t\"1.右肺上野链状致密影，周围条片状高密度影。右侧胸壁及颈部少量积气。\n" +
                "2.双肺纹理增多、模糊。\n" +
                "3.心影不大。\"\t备注:\t47岁\t女\t检查时间:\t2023/9/15 13:22\n" +
                "asdfs\t姓名:\t李茹仙\t影像号:\t1664808\t诊断:\t\"\n" +
                "1.双肺纹理增多、模糊，双肺中下野渗出、实变灶。\n" +
                "2.心影增大。\n" +
                "3.双侧胸腔积液并胸膜增厚、粘连；左侧胸壁少许积气。\"\t备注:\t69岁\t女\t检查时间:\t2023/9/15 13:24\n" +
                "asdfs\t姓名:\t余菊芬\t影像号:\t1954085\t诊断:\t\"1.双肺纹理增多、模糊。\n" +
                "2.左肺上野链状致密影，周围条片状高密度影，左侧胸腔引流管留置。左侧胸壁及颈部积气。\n" +
                "3.心影不大。\"\t备注:\t48岁\t女\t检查时间:\t2023/9/15 13:25\n" +
                "asdfs\t姓名:\t卞蕊婷\t影像号:\t1952651\t诊断:\t\"1、双肺纹理增多、紊乱。\n" +
                "2、右肺术后，术区见条状致密影及少许渗出影，较前（2023-9-14)片稍有吸收。\n" +
                "3、心影不大。\n" +
                "4、双侧胸膜腔未见明显积液征象。\"\t备注:\t42岁\t女\t检查时间:\t2023/9/15 13:28\n" +
                "asdfs\t姓名:\t廖能芬\t影像号:\t1952031\t诊断:\t\"1.右肺体积减小，右肺中下野见条状致密影，周围片状密度增高影，右侧胸部见2根引流管留置，考虑术后，请结合临床。\n" +
                "2.双肺纹理增多紊乱。\n" +
                "3.双侧肋膈角变钝，双侧胸腔少量积液可能。\n" +
                "4.心影稍增大。\n" +
                "5.右肺尖区域见管状影，位置请结合临床。\"\t备注:\t67岁\t女\t检查时间:\t2023/9/15 13:30\n" +
                "asdfs\t姓名:\t缪聪合\t影像号:\t1952713\t诊断:\t\"\n" +
                "1、双肺纹理增多、紊乱，双肺下叶片状高密度影。\n" +
                "2、左肺术后改变：左肺门增大、结构紊乱，术区见条状致密影及少量渗出影，左侧少量气胸；左侧胸腔见影流管置管影，皮下积气较前（2023-9-14）片有吸收。\n" +
                "3、心影增大；上纵隔影增宽。\"\t备注:\t56岁\t男\t检查时间:\t2023/9/15 13:31\n" +
                "asdfs\t姓名:\t施凤莲\t影像号:\t1945253\t诊断:\t\"1.右肺术后：右肺下野链状致密影，周围片状高密度影。右侧胸腔引流管留置。\n" +
                "2.双肺纹理模糊，左肺下野片状高密度影。\n" +
                "3.主动脉壁钙化，心影不大。\n" +
                "4.右侧肋膈角稍模糊。\"\t备注:\t59岁\t女\t检查时间:\t2023/9/15 13:35\n" +
                "asdfs\t姓名:\t龚树萍\t影像号:\t1954811\t诊断:\t\"1.双肺纹理增多、模糊、紊乱，双肺网格状改变。双肺多发结节影。\n" +
                "2.心影增大，主动脉壁钙化；右下肺动脉干增粗，右肺门影增大、浓密。\n" +
                "3.左侧肋膈角显示不清。\n" +
                "4.右侧股骨粗隆间骨折，周围软组织肿胀。\"\t备注:\t83岁\t女\t检查时间:\t2023/9/15 13:42\n" +
                "asdfs\t姓名:\t李勇\t影像号:\t1925745\t诊断:\t\"1.慢性支气管炎、肺气肿征象，伴两肺散在渗出性病灶。\n" +
                "2.双侧肺门影浓密。\n" +
                "3.主动脉型心影、主动脉弓钙化。\n" +
                "4.右侧肋膈角显示不清。\n" +
                "5.胸部见置管影；气管插管术后改变，请结合临床。\"\t备注:\t67岁\t男\t检查时间:\t2023/9/15 13:49\n" +
                "asdfs\t姓名:\t李菊英\t影像号:\t1957515\t诊断:\t\"1.双肺纹理增多、模糊，左肺下野片状高密度影，建议必要时CT进一步检查。\n" +
                "2.主动脉走行迂曲，心影不大。\n" +
                "3.气管软化试验阴性\"\t备注:\t51岁\t女\t检查时间:\t2023/9/15 13:55\n" +
                "asdfs\t姓名:\t赵小文\t影像号:\t1956144\t诊断:\t\"1.右肺下叶见团片状密度增高影，请结合临床及相关检查。\n" +
                "2.双侧肺门影浓密。\n" +
                "3.心影不大。\n" +
                "4.右侧肋膈角显示不清。\n" +
                "\"\t备注:\t58岁\t男\t检查时间:\t2023/9/15 13:58\n" +
                "asdfs\t姓名:\t罗鸣放\t影像号:\t1420708\t诊断:\t\"1.胸椎骨质增生，考虑退行性改变。\n" +
                "2.胸4-12多发胸椎椎体变扁，请结合临床及CT检查。\"\t备注:\t64岁\t女\t检查时间:\t2023/9/15 13:59\n" +
                "asdfs\t姓名:\t汤惠琼\t影像号:\t1264769\t诊断:\t\"气管软化试验阴性。\n" +
                "\"\t备注:\t76岁\t女\t检查时间:\t2023/9/15 14:01\n" +
                "asdfs\t姓名:\t余婷\t影像号:\t1802643\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\"\t备注:\t31岁\t女\t检查时间:\t2023/9/15 14:01\n" +
                "asdfs\t姓名:\t宁佐明\t影像号:\t1953473\t诊断:\t腰椎退变征象；腰4、5椎体内固定术后改变，内固定器未见脱落断裂征象，请结合临床。\t备注:\t55岁\t男\t检查时间:\t2023/9/15 14:02\n" +
                "asdfs\t姓名:\t蒋林莉\t影像号:\t1957537\t诊断:\t右足第1楔跖关节间隙变窄，骨质密度增高，多系增生性关节炎。\t备注:\t26岁\t女\t检查时间:\t2023/9/15 14:02\n" +
                "asdfs\t姓名:\t杨月波\t影像号:\t1957477\t诊断:\t\"1、双肺野未见明显渗出、实变病灶。\n" +
                "2、心影稍大。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\n" +
                "4、右侧肱骨头及关节盂形态不规则并关节比邻关系异常，关节周围软组织密度不均，请结合临床相关病史及检查。\"\t备注:\t52岁\t女\t检查时间:\t2023/9/15 14:03\n" +
                "asdfs\t姓名:\t刘曦羽\t影像号:\t1957535\t诊断:\t右腕关节未见明显骨质中断征象。建议必要时复查。\t备注:\t16岁\t男\t检查时间:\t2023/9/15 14:05\n" +
                "asdfs\t姓名:\t鲁绍美\t影像号:\t1957541\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\n" +
                "4、所示双侧膈上肋骨骨质未见明确中断、错位征象，必要时肋骨CT三维成像检查。\n" +
                "5、胸椎及片内腰椎退行性变。\"\t备注:\t53岁\t女\t检查时间:\t2023/9/15 14:05\n" +
                "asdfs\t姓名:\t张虎有\t影像号:\t1957468\t诊断:\t\"1、左侧踝关节退变征象。\n" +
                "2、腰椎退变征象，过伸过屈活动度欠佳，椎体未见滑脱表现，双侧斜位椎弓峡部未见断裂征象，请结合临床。\"\t备注:\t70岁\t男\t检查时间:\t2023/9/15 14:07\n" +
                "asdfs\t姓名:\t赵恒丽\t影像号:\t1957545\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\"\t备注:\t37岁\t女\t检查时间:\t2023/9/15 14:09\n" +
                "asdfs\t姓名:\t王思明\t影像号:\t1924402\t诊断:\t右足第1、2跖骨及跟骨骨折内固定术后复查：本次片骨折断端对位良好，骨折线模糊，见骨痂影，内固定未见异常；右足骨质疏松。\t备注:\t16岁\t男\t检查时间:\t2023/9/15 14:10\n" +
                "asdfs\t姓名:\t闫丽春\t影像号:\t1526599\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\"\t备注:\t27岁\t女\t检查时间:\t2023/9/15 14:11\n" +
                "asdfs\t姓名:\t万如丹\t影像号:\t1957553\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\"\t备注:\t34岁\t女\t检查时间:\t2023/9/15 14:13\n" +
                "asdfs\t姓名:\t徐庆通\t影像号:\t1957486\t诊断:\t\"1.双肺、心、膈未见明显异常。\n" +
                "2.颈椎退行性改变。\"\t备注:\t60岁\t男\t检查时间:\t2023/9/15 14:13\n" +
                "asdfs\t姓名:\t寇文琴\t影像号:\t1957561\t诊断:\t腰椎退行性变表现。\t备注:\t53岁\t女\t检查时间:\t2023/9/15 14:15\n" +
                "asdfs\t姓名:\t苏敬生\t影像号:\t1954016\t诊断:\t\"1.腰3-骶1椎体内固定术后，相应椎间隙密度增高，周围软组织见管影，请结合临床及CT检查。\n" +
                "2.腰椎退行性改变。\"\t备注:\t60岁\t男\t检查时间:\t2023/9/15 14:18\n" +
                "asdfs\t姓名:\t文武\t影像号:\t1949530\t诊断:\t\"1.两肺散在渗出性病灶。\n" +
                "2.双侧肺门影浓密。\n" +
                "3.主动脉型心影。\n" +
                "4.右侧肋膈角显示不清。\n" +
                "5.气管插管术后改变，请结合临床。\"\t备注:\t57岁\t男\t检查时间:\t2023/9/15 14:19\n" +
                "asdfs\t姓名:\t李琼丽\t影像号:\t1458005\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\"\t备注:\t38岁\t女\t检查时间:\t2023/9/15 14:19\n" +
                "asdfs\t姓名:\t陈璇\t影像号:\t1957568\t诊断:\t\"1、右胫骨下端见两枚高密度螺钉影及胫腓骨下端见管状稍低密度影，右踝关节骨质密度欠均匀，未见破坏征象。请结合临床。\n" +
                "2、右踝关节毗邻关系正常，未见脱位征象。\"\t备注:\t21岁\t女\t检查时间:\t2023/9/15 14:20\n" +
                "asdfs\t姓名:\t朱正花\t影像号:\t1957425\t诊断:\t\"\n" +
                "1.双侧乳腺肿块（右乳多发，左乳单发），纤维腺瘤或囊肿待鉴别。BI-RADS：3\n" +
                "2.双侧乳腺钙化灶。BI-RADS：2\"\t备注:\t50岁\t女\t检查时间:\t2023/9/15 14:20\n" +
                "asdfs\t姓名:\t李祥\t影像号:\t1957533\t诊断:\t\"1、双肺未见明确渗出、实变灶。\n" +
                "2、心、膈未见明确异常。\"\t备注:\t39岁\t男\t检查时间:\t2023/9/15 14:20\n" +
                "asdfs\t姓名:\t张艳芳\t影像号:\t1719940\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "3、双侧膈顶光整，双侧胸膜腔未见积液征象。\n" +
                "4、胸椎轻度侧弯表现。\"\t备注:\t44岁\t女\t检查时间:\t2023/9/15 14:21\n" +
                "asdfs\t姓名:\t李梅华\t影像号:\t1956034\t诊断:\t\"1.两肺纹理增多\n" +
                "2.双侧肺门影浓密。\n" +
                "3.主动脉型心影。\n" +
                "4.两侧肋膈角显示尚清。\n" +
                "\"\t备注:\t82岁\t女\t检查时间:\t2023/9/15 14:21\n" +
                "asdfs\t姓名:\t李培章\t影像号:\t1957532\t诊断:\t 双肺、心、膈未见明显异常。\t备注:\t41岁\t男\t检查时间:\t2023/9/15 14:23\n" +
                "asdfs\t姓名:\t雷小芬\t影像号:\t1940667\t诊断:\t\"1、颈椎、胸椎、腰椎退行性改变。\n" +
                "2、胸12-骶椎、骨盆术后改变。\"\t备注:\t53岁\t女\t检查时间:\t2023/9/15 14:23\n" +
                "asdfs\t姓名:\t何吉芳\t影像号:\t1957525\t诊断:\t\"1、双肺未见明确渗出性病灶影。\n" +
                "2、心外形未见增大表现。\n" +
                "\"\t备注:\t49岁\t女\t检查时间:\t2023/9/15 14:24\n" +
                "asdfs\t姓名:\t孔会珍\t影像号:\t1926421\t诊断:\t\"1、左胫骨下段见金属内固定器影，内固定器未见断裂、移位、脱落，呈术后改变，胫骨下段横形低密度影对比2023年8月24日片变细；请结合临床。\n" +
                "2、左踝关节及片内左足退行性变表现。\"\t备注:\t63岁\t女\t检查时间:\t2023/9/15 14:25\n" +
                "asdfs\t姓名:\t马帅\t影像号:\t1957457\t诊断:\t\"1.双肺未见确切渗出、实变影。\n" +
                "2.心、膈未见特殊X线征象。\"\t备注:\t25岁\t男\t检查时间:\t2023/9/15 14:26\n" +
                "asdfs\t姓名:\t张长庚\t影像号:\t1956842\t诊断:\t\"1、右上胸腔少许积气，较前变化不明显。\n" +
                "2、双肺慢性支气管炎、肺气肿征象，肺大疱。并双肺散在间质纤维化合并感染可能性大。\n" +
                "3、左侧胸膜增厚、粘连、钙化，纵隔左移。\n" +
                "4、主动脉钙化。心影不大。\n" +
                "\n" +
                "\"\t备注:\t78岁\t男\t检查时间:\t2023/9/15 14:27\n" +
                "asdfs\t姓名:\t徐次菊\t影像号:\t1906606\t诊断:\t\"1.双肺纹理增多、模糊。\n" +
                "2.心影稍增大。上腔静脉走行区置管影。\"\t备注:\t54岁\t女\t检查时间:\t2023/9/15 14:27\n" +
                "asdfs\t姓名:\t王彦\t影像号:\t1884714\t诊断:\t左侧膝关节退行性变；腓骨头稍膨大。\t备注:\t66岁\t男\t检查时间:\t2023/9/15 14:28\n" +
                "asdfs\t姓名:\t郑贵云\t影像号:\t1957519\t诊断:\t\"1、双肺未见明确渗出、实变影。\n" +
                "2、心、膈未见特殊X线征象。\"\t备注:\t37岁\t男\t检查时间:\t2023/9/15 14:29\n" +
                "asdfs\t姓名:\t张永珍\t影像号:\t1949944\t诊断:\t\"1.右骶髂关节间隙显示不清。余骨盆骨质未见明显异常。\n" +
                "2.片内示腰椎术后改变。\"\t备注:\t54岁\t女\t检查时间:\t2023/9/15 14:30\n" +
                "asdfs\t姓名:\t张文光\t影像号:\t1807797\t诊断:\t腹部正位片未见异常。\t备注:\t75岁\t男\t检查时间:\t2023/9/15 14:31\n" +
                "asdfs\t姓名:\t范常香\t影像号:\t1957557\t诊断:\t\"1、心影稍大。\n" +
                "2、肺、膈未见明显异常。\"\t备注:\t48岁\t女\t检查时间:\t2023/9/15 14:33\n" +
                "asdfs\t姓名:\t金建华\t影像号:\t1918990\t诊断:\t\"1、右跟骨粉碎性骨折内外固定术后复查：本次片骨折断端对位良好，见骨痂影，内固定未见断裂、移位、脱落；腓骨下段骨折，骨折断端对位好，骨痂生长良好；见高密度外固定影，对比2023年8月23日片骨痂影稍多。请结合临床。\n" +
                "2、右踝关节及片内右足骨质疏松表现。\"\t备注:\t59岁\t男\t检查时间:\t2023/9/15 14:35\n" +
                "asdfs\t姓名:\t邢一鹏\t影像号:\t1957522\t诊断:\t\"1.双肺纹理增多、模糊，右肺上野结节影，建议必要时CT进一步检查。\n" +
                "2.心影不大。\"\t备注:\t64岁\t男\t检查时间:\t2023/9/15 14:36\n" +
                "asdfs\t姓名:\t祖黑妹\t影像号:\t1951539\t诊断:\t\"1.双肺纹理增多、模糊。\n" +
                "2.心影增大，主动脉弓、升主动脉走行区致密影，上纵隔增宽。胸骨呈术后改变。\n" +
                "3.右上纵隔区置管影；左上腹区管状影。\"\t备注:\t53岁\t女\t检查时间:\t2023/9/15 14:36\n" +
                "asdfs\t姓名:\t倪志强\t影像号:\t1956280\t诊断:\t\"1、腰椎骨质未见异常征象。\n" +
                "2、双侧骶髂关节密度增高，间隙变窄，请结合CT检查。\n" +
                "3、右股骨头及左股骨上段粗隆下结节状高密度影，多系骨岛。\"\t备注:\t27岁\t男\t检查时间:\t2023/9/15 14:37\n" +
                "asdfs\t姓名:\t华炳珍\t影像号:\t1957552\t诊断:\t\"1.双肺纹理增多、模糊。\n" +
                "2.心影增大，升主动脉增宽、迂曲。\"\t备注:\t71岁\t女\t检查时间:\t2023/9/15 14:38\n" +
                "asdfs\t姓名:\t和贵城\t影像号:\t1883867\t诊断:\t\"1.胸部术后：胸骨区、主动脉区呈术后改变，心影增大。双肺纹理增多，其间散在渗出性病灶，提示感染。\n" +
                "2.两肺门影浓密。\n" +
                "3.肋膈角显示欠清楚。胸部见导管影。\"\t备注:\t61岁\t男\t检查时间:\t2023/9/15 14:38\n" +
                "asdfs\t姓名:\t张弘\t影像号:\t1957564\t诊断:\t\"1.双肺纹理增多、模糊。\n" +
                "2.右侧肺门影浓密。\n" +
                "3.主动脉结突出，心影稍增大。\"\t备注:\t54岁\t男\t检查时间:\t2023/9/15 14:39\n" +
                "asdfs\t姓名:\t杨禹\t影像号:\t1698113\t诊断:\t\"1、双肺未见明确渗出、实变灶。\n" +
                "2、心、膈未见明确异常。\"\t备注:\t34岁\t男\t检查时间:\t2023/9/15 14:39\n" +
                "asdfs\t姓名:\t毕文山\t影像号:\t1953870\t诊断:\t\"\n" +
                "1.双肺纹理增多、模糊。\n" +
                "2.心影增大，主动脉走行区直径影，胸骨术后改变。\n" +
                "3.左侧肋膈角显示不清。\n" +
                "4.右上纵隔区置管影。\"\t备注:\t37岁\t男\t检查时间:\t2023/9/15 14:40\n" +
                "asdfs\t姓名:\t施绍珍\t影像号:\t1035227\t诊断:\t\"1、双肺纹理增多。\n" +
                "2、心、膈未见特殊X线征象。\n" +
                "3、脊柱轻度侧弯。\"\t备注:\t59岁\t女\t检查时间:\t2023/9/15 14:40\n" +
                "asdfs\t姓名:\t徐慧玉\t影像号:\t1957418\t诊断:\t腰椎退行性改变。\t备注:\t43岁\t女\t检查时间:\t2023/9/15 14:40\n" +
                "asdfs\t姓名:\t曹燕\t影像号:\t1957521\t诊断:\t\"1、心、肺、膈未见明显异常。\n" +
                "2、气管软化试验阴性。\"\t备注:\t30岁\t女\t检查时间:\t2023/9/15 14:42\n" +
                "asdfs\t姓名:\t杨丽\t影像号:\t1957562\t诊断:\t\"\n" +
                "1、双肺纹理增多、紊乱。\n" +
                "2、双肺野见斑片状渗出、实变病灶。\n" +
                "3、心影不大；上纵隔影未见增宽。\n" +
                "4、双侧胸膜腔未见积液征象。  \"\t备注:\t45岁\t女\t检查时间:\t2023/9/15 14:42\n" +
                "asdfs\t姓名:\t史春情\t影像号:\t1957516\t诊断:\t\"1.双肺、心、膈未见明显异常。\n" +
                "2.气管软化试验阴性。\"\t备注:\t38岁\t女\t检查时间:\t2023/9/15 14:45\n" +
                "asdfs\t姓名:\t何吉芳\t影像号:\t1957525\t诊断:\t\"\n" +
                "1.左侧乳腺肿块伴结构扭曲，乳腺MT不能除外，请结合临床。BI-RADS：4A\n" +
                "2.双侧乳腺钙化灶。BI-RADS：3\"\t备注:\t49岁\t女\t检查时间:\t2023/9/15 14:46\n" +
                "asdfs\t姓名:\t郑云娜\t影像号:\t1957407\t诊断:\t\"1.双肺纹理增多、模糊。\n" +
                "2.心影增大。\"\t备注:\t46岁\t女\t检查时间:\t2023/9/15 14:46\n" +
                "asdfs\t姓名:\t陈群\t影像号:\t1939513\t诊断:\t右桡骨远端骨折内固定术后复查：本次片骨折断端对位良好，骨折线模糊，未见明确骨痂影，内固定未见异常。\t备注:\t52岁\t女\t检查时间:\t2023/9/15 14:46\n" +
                "asdfs\t姓名:\t李金焕\t影像号:\t1932920\t诊断:\t右肾区高密度结节影。左侧泌尿系见双J管留置。\t备注:\t50岁\t女\t检查时间:\t2023/9/15 14:47\n" +
                "asdfs\t姓名:\t周慧立\t影像号:\t1665192\t诊断:\t\"1.双肺、心、膈未见明显异常。\n" +
                "2.气管软化试验阴性。\"\t备注:\t37岁\t女\t检查时间:\t2023/9/15 14:48\n" +
                "asdfs\t姓名:\t王瑜\t影像号:\t1952536\t诊断:\t泌尿系双“J”导管置入术后改变，现片示泌尿系结石征象不明确，请结合临床。\t备注:\t44岁\t男\t检查时间:\t2023/9/15 14:49\n" +
                "asdfs\t姓名:\t刘正刚\t影像号:\t1957601\t诊断:\t腰椎退行性改变表现。\t备注:\t56岁\t男\t检查时间:\t2023/9/15 14:50\n" +
                "asdfs\t姓名:\t朱忠云\t影像号:\t1957143\t诊断:\t\"1.双肺纹理增多、模糊，双肺下野网格状影。\n" +
                "2.心影不大。\"\t备注:\t65岁\t男\t检查时间:\t2023/9/15 14:51\n" +
                "asdfs\t姓名:\t张荣\t影像号:\t1415860\t诊断:\t\"1、腰椎退变征象，腰段脊柱轻度左突右弯；过伸过屈活动度欠佳，椎体未见滑脱表现，双侧斜位椎弓峡部未见断裂征象。\n" +
                "2、颈椎生理曲度变直。\"\t备注:\t42岁\t女\t检查时间:\t2023/9/15 14:51\n" +
                "asdfs\t姓名:\t俞峰\t影像号:\t1954565\t诊断:\t\"\n" +
                "\n" +
                "右膝关节退行性改变。\"\t备注:\t48岁\t男\t检查时间:\t2023/9/15 14:54\n" +
                "asdfs\t姓名:\t王文亮\t影像号:\t1957563\t诊断:\t\"1.慢性支气管炎，肺气肿。\n" +
                "2.双肺野片状、结节状高密度影。\n" +
                "3.心影增大，主动脉壁钙化，心胸比例约0.54。\n" +
                "4.双侧肋膈角显示不清。\"\t备注:\t74岁\t男\t检查时间:\t2023/9/15 14:54\n" +
                "asdfs\t姓名:\t袁杏琴\t影像号:\t1957603\t诊断:\t左侧跟骨骨质未见破坏征象。\t备注:\t57岁\t女\t检查时间:\t2023/9/15 14:55\n" +
                "asdfs\t姓名:\t黎会仙\t影像号:\t1947299\t诊断:\t气管软化试验阴性\t备注:\t74岁\t女\t检查时间:\t2023/9/15 14:55\n" +
                "asdfs\t姓名:\t邓慧良\t影像号:\t1957606\t诊断:\t双侧茎突偏长。\t备注:\t43岁\t女\t检查时间:\t2023/9/15 14:57\n" +
                "asdfs\t姓名:\t计路良\t影像号:\t1954054\t诊断:\t颈椎退变征象；颈4-6椎体内骨定术后改变，内固定器未见脱落断裂征象，请结合临床。\t备注:\t43岁\t男\t检查时间:\t2023/9/15 14:57\n" +
                "asdfs\t姓名:\t张云唤\t影像号:\t1952991\t诊断:\t\"1、腰椎骨折内固定术后改变。\n" +
                "2、腰椎退行性变。\"\t备注:\t74岁\t女\t检查时间:\t2023/9/15 14:58\n" +
                "asdfs\t姓名:\t张伟青\t影像号:\t1907589\t诊断:\t\"1.右膝关节置换术后改变。\n" +
                "2.右股骨下段内侧缘骨皮质中断，局部可见不规则骨性密度影。请结合临床。\"\t备注:\t60岁\t女\t检查时间:\t2023/9/15 15:02\n";
        dataText = dataText.replaceAll("\t", "");
        dataText = dataText.replaceAll("\"", "");
        dataText = dataText.replaceAll("\n", "");
        dataText = dataText.replaceAll("/", ".");
        String[] splitData = dataText.split("asdfs");
        int sum = 0;
        for (String text : splitData) {
            sum += 1;
            int nameStart = text.lastIndexOf("姓名:");
            int videoStart = text.lastIndexOf("影像号:");
            int diagnosisStart = text.lastIndexOf("诊断:");
            int noteStart = text.lastIndexOf("备注:");
            int timeStart = text.lastIndexOf("检查时间:");
            String name = text.substring(nameStart + 3, videoStart);
            String video = text.substring(videoStart + 4, diagnosisStart);
            String diagnosis = text.substring(diagnosisStart + 3, noteStart);
            String note = text.substring(noteStart + 3, timeStart);
            String time = text.substring(timeStart + 5);
            System.out.println("姓名：" + name + "影像号：" + video + "诊断:" + diagnosis + "备注:" + note + "检查时间:" + time);
            String json = "{\"masterId\":4314,\"scheduleId\":15317,\"roundMasterRecordDetails\":[{\"answer\":\"符涵珍\",\"configItemId\":17919,\"sort\":1},{\"answer\":\"1955484\",\"configItemId\":17920,\"sort\":2},{\"answer\":\"2023.09.15  17:46\",\"configItemId\":17921,\"sort\":3},{\"answer\":\"双髋关节、双膝关节、双踝关节退行性改变。\",\"configItemId\":17922,\"sort\":4},{\"answer\":\"79岁\\t女\",\"configItemId\":17923,\"sort\":5}]}";
            json = json.replace("符涵珍", name)
                    .replace("1955484", video)
                    .replace("2023.09.15  17:46", time)
                    .replace("双髋关节、双膝关节、双踝关节退行性改变。", diagnosis)
                    .replace("79岁\\t女", note);
//            System.out.println(json);
            postRequest(json);
        }
        System.out.println(sum);
    }

    public static String postRequest(String json) throws IOException {
        Response signRsp = null;
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            //封装请求体
            RequestBody newBody = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder()
                    .url("https://zhike.samjan.com/csm/roundMasterRecord/add")
                    .method("POST", newBody)
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0MzAyMjMxOTk5MDQxNjcyMjIiLCJjcmVhdGVkIjoxNzAyMTk1NjE4Nzk1LCJwcmVmaXgiOiI0MzAyMjMxOTk5MDQxNjcyMjI0NDQzMjEiLCJleHAiOjE3MDIyNTU2MTgsInVybCI6Imh0dHBzOi8vemhpa2Uuc2FtamFuLmNvbS9wbGF0Zm9ybS90b2dldGhlci8ifQ.LNN3Utkxmw1CJC_CDJZ8bMPp9YHXvGXg4_rQCvp2P-9_MycZGvK7-s1kf3gpCikf5WkDAZcvbj2nqDQbS3TNaw")
                    .addHeader("Cookie", "HWWAFSESID=bbd157911a1ad99451; HWWAFSESTIME=1702195435247").build();
            //执行请求
            signRsp = client.newCall(request).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //解析
        assert signRsp != null;
        assert signRsp.body() != null;
        String result = signRsp.body().string();
        JSONObject jsonObject = JSON.parseObject(result);
        if (!jsonObject.get("code").equals(0)) {
            System.out.println(json);
            System.out.println(result);
        }
        return result;
    }

    public static void dataConvert() {
        String filePath = "src/main/java/com/dn/algorithm/leetcode/hot100/X线.xls";
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0); // 假设你要读取第一个工作表
            for (int i = 1; i <= 100; i++) {
                Row row = sheet.getRow(i); // 假设你要读取第i行
                if (row != null) {
                    StringBuilder rowContent = new StringBuilder();
                    for (Cell cell : row) {
                        // 读取单元格数据并进行相应的处理
                        String content = "";
                        if (cell.getCellType().equals(CellType.NUMERIC)) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d H:m");
                            content = format.format(cell.getDateCellValue());
                            content = content.replaceAll("/", ".");
                        } else {
                            content = cell.getStringCellValue();
                        }
                        rowContent.append(content).append("split");
                    }
                    if (rowContent.length() > 0) {
                        rowContent.delete(rowContent.length() - 5, rowContent.length());
                    }
                    String rowContext = new String(rowContent);
                    String[] splits = rowContext.split("split");
                    String name = splits[0];
                    String video = splits[1];
                    String diagnosis = splits[2];
                    String note = splits[3] + splits[4];
                    String time = splits[5];
//                    System.out.println("姓名：" + name + "影像号：" + video + "诊断:" + diagnosis + "备注:" + note + "检查时间:" + time);
                    String json = "{\"masterId\":4314,\"scheduleId\":15317,\"roundMasterRecordDetails\":[{\"answer\":\"符涵珍\",\"configItemId\":17919,\"sort\":1},{\"answer\":\"1955484\",\"configItemId\":17920,\"sort\":2},{\"answer\":\"2023.09.15  17:46\",\"configItemId\":17921,\"sort\":3},{\"answer\":\"双髋关节、双膝关节、双踝关节退行性改变。\",\"configItemId\":17922,\"sort\":4},{\"answer\":\"79岁\\t女\",\"configItemId\":17923,\"sort\":5}]}";
                    json = json.replace("符涵珍", name)
                            .replace("1955484", video)
                            .replace("2023.09.15  17:46", time)
                            .replace("双髋关节、双膝关节、双踝关节退行性改变。", diagnosis)
                            .replace("79岁\\t女", note);
                    System.out.println("第" + i + "位:" + json);
//                    postRequest(json);
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //只能单线程情况下保证有序
    public static void test01() {
        Thread threadA = new Thread(() -> {
            System.out.println("AAAA");
        });
        Thread threadB = new Thread(() -> {
            try {
                threadA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BBBB");
        });
        Thread threadC = new Thread(() -> {
            try {
                threadB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("CCCC");
        });
        threadC.start();
        threadB.start();
        threadA.start();
    }

    //线程池循环打印abc
    public static void test02() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        int x = 0;
        while (x < 100) {
            scheduledExecutorService.submit(getTask("A")).get();
            scheduledExecutorService.submit(getTask("B")).get();
            scheduledExecutorService.submit(getTask("C")).get();
            x++;
        }
        scheduledExecutorService.shutdown();

    }

    private static Callable<String> getTask(String task) {
        return () -> {
            System.out.println(task);
            return task;
        };
    }

    private static final Object lock = new Object();
    private static volatile int status = 1;

    public static void test03() {
        Thread threadA = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (lock) {
                        while (status != 1) {
                            lock.wait();
                        }
                        System.out.println("A");
                        status = 2;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (lock) {
                        while (status != 2) {
                            lock.wait();
                        }
                        System.out.println("B");
                        status = 3;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (lock) {
                        while (status != 3) {
                            lock.wait();
                        }
                        System.out.println("C");
                        status = 1;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();

    }

}
