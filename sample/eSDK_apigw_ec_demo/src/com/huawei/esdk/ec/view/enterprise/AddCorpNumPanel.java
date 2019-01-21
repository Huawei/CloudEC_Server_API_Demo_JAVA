package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.AddCorpNumBean;
import com.huawei.esdk.ec.bean.enterprise.MMTelExtensionBean;
import com.huawei.esdk.ec.bean.enterprise.NumberCallOutRightsVo;
import com.huawei.esdk.ec.bean.sp.RightsEnum;
import com.huawei.esdk.ec.bean.sp.RightsStatusEnum;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
import com.huawei.esdk.ec.view.sp.AddCorpPanel;
/**
 * 增加企业号码视图
 * Increase company number view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class AddCorpNumPanel extends JPanel
{

	private static final long serialVersionUID = 551101129946144512L;
	
	private static final Logger LOGGER = LogManager.getLogger(AddCorpPanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel numberLabel = new JLabel("number:");
	private JTextField numberField = new JTextField(10);
	
	private JLabel shortNumLabel = new JLabel("shortNum:");
	private JTextField shortNumField = new JTextField(10);
	
	private JLabel pwdLabel = new JLabel("pwd:");
	private JPasswordField pwdField = new JPasswordField(10);
	
	private JLabel didNumLabel = new JLabel("didNum:");
	private JTextField didNumField = new JTextField(10);
	
	private JLabel cxTypeLabel = new JLabel("cxType:");
	private JTextField cxTypeField = new JTextField(10);
	
	private JLabel statusLabel = new JLabel("status:");
	private JTextField statusField = new JTextField(10);
	
	private JLabel odspModeLabel = new JLabel("odspMode:");
	private JTextField odspModeField = new JTextField(10);
	
	private JLabel outOcxPfxLabel = new JLabel("outOcxPfx:");
	private JTextField outOcxPfxField = new JTextField(10);
	
	private JLabel voipDomainLabel = new JLabel("voipDomain:");
	private JTextField voipDomainField = new JTextField(10);
	
	private JLabel sipServerGrpLabel = new JLabel("sipServerGrp:");
	private JTextField sipServerGrpField = new JTextField(10);
	
	private JLabel localGatewayLabel = new JLabel("localGateway:");
	private JTextField localGatewayField = new JTextField(10);
	
	private JLabel localGatewaySyncLabel = new JLabel("localGatewaySync:");
	private JTextField localGatewaySyncField = new JTextField(10);
	
	private JLabel userPriorityLabel = new JLabel("userPriority:");
	private JTextField userPriorityField = new JTextField(10);
	
	private JLabel isConfAccessNumberLabel = new JLabel("isConfAccessNumber:");
	private JTextField isConfAccessNumberField = new JTextField(10);
	
	private JLabel numberTypeLabel = new JLabel("numberType:");
	private JTextField numberTypeField = new JTextField(10);
	
	//用户扩展业务
	//Users expand their business
	private JLabel callSrcCodeLabel = new JLabel("callSrcCode:");
	private JTextField callSrcCodeField = new JTextField(10);
	
	private JLabel iupSubscribeLabel = new JLabel("iupSubscribe:");
	private JTextField iupSubscribeField = new JTextField(10);
	
	//呼叫权限
	private JLabel rightsEnumLabel = new JLabel("rightsEnum:");
	private JTextField rightsEnumField = new JTextField(10);
	
	private JLabel rightsStatusEnumLabel = new JLabel("rightsStatusEnum:");
	private JTextField rightsStatusEnumField = new JTextField(10);
	
	// 前转呼出权限
	// Forward call rights
	private JLabel defaultCallOutLabel = new JLabel("defaultCallOut:");
	private JTextField defaultCallOutField = new JTextField(10);
	
	private JLabel localCallOutLabel = new JLabel("localCallOut:");
	private JTextField localCallOutField = new JTextField(10);
	
	private JLabel localTollCallOutLabel = new JLabel("localTollCallOut:");
	private JTextField localTollCallOutField = new JTextField(10);
	
	private JLabel nationalTollCallOutLabel = new JLabel("nationalTollCallOut:");
	private JTextField nationalTollCallOutField = new JTextField(10);
	
	private JLabel internationalTollCallOutLabel = new JLabel("internationalTollCallOut:");
	private JTextField internationalTollCallOutField = new JTextField(10);
	
	private JLabel intraLocalTollCallOutLabel = new JLabel("intraLocalTollCallOut:");
	private JTextField intraLocalTollCallOutField = new JTextField(10);
	
	private JLabel intraNationalTollCallOutLabel = new JLabel("intraNationalTollCallOut:");
	private JTextField intraNationalTollCallOutField = new JTextField(10);
	
	private JLabel ccoaOut1Label = new JLabel("ccoaOut1:");
	private JTextField ccoaOut1Field = new JTextField(10);
	
	private JLabel ccoaOut2Label = new JLabel("ccoaOut2:");
	private JTextField ccoaOut2Field = new JTextField(10);
	
	private JLabel ccoaOut3Label = new JLabel("ccoaOut3:");
	private JTextField ccoaOut3Field = new JTextField(10);
	
	private JLabel ccoaOut4Label = new JLabel("ccoaOut4:");
	private JTextField ccoaOut4Field = new JTextField(10);
	
	private JLabel ccoaOut5Label = new JLabel("ccoaOut5:");
	private JTextField ccoaOut5Field = new JTextField(10);
	
	private JLabel ccoaOut6Label = new JLabel("ccoaOut6:");
	private JTextField ccoaOut6Field = new JTextField(10);
	
	private JLabel ccoaOut7Label = new JLabel("ccoaOut7:");
	private JTextField ccoaOut7Field = new JTextField(10);
	
	private JLabel ccoaOut8Label = new JLabel("ccoaOut8:");
	private JTextField ccoaOut8Field = new JTextField(10);
	
	private JLabel ccoaOut9Label = new JLabel("ccoaOut9:");
	private JTextField ccoaOut9Field = new JTextField(10);
	
	private JLabel ccoaOut10Label = new JLabel("ccoaOut10:");
	private JTextField ccoaOut10Field = new JTextField(10);
	
	private JLabel ccoaOut11Label = new JLabel("ccoaOut11:");
	private JTextField ccoaOut11Field = new JTextField(10);
	
	private JLabel ccoaOut12Label = new JLabel("ccoaOut12:");
	private JTextField ccoaOut12Field = new JTextField(10);
	
	private JLabel ccoaOut13Label = new JLabel("ccoaOut13:");
	private JTextField ccoaOut13Field = new JTextField(10);
	
	private JLabel ccoaOut14Label = new JLabel("ccoaOut14:");
	private JTextField ccoaOut14Field = new JTextField(10);
	
	private JLabel ccoaOut15Label = new JLabel("ccoaOut15:");
	private JTextField ccoaOut15Field = new JTextField(10);
	
	private JLabel ccoaOut16Label = new JLabel("ccoaOut16:");
	private JTextField ccoaOut16Field = new JTextField(10);
	
	private JLabel ccoaOut17Label = new JLabel("ccoaOut17:");
	private JTextField ccoaOut17Field = new JTextField(10);
	
	private JLabel ccoaOut18Label = new JLabel("ccoaOut18:");
	private JTextField ccoaOut18Field = new JTextField(10);
	
	private JLabel ccoaOut19Label = new JLabel("ccoaOut19:");
	private JTextField ccoaOut19Field = new JTextField(10);
	
	private JLabel ccoaOut20Label = new JLabel("ccoaOut20:");
	private JTextField ccoaOut20Field = new JTextField(10);
	
	private JLabel ccoaOut21Label = new JLabel("ccoaOut21:");
	private JTextField ccoaOut21Field = new JTextField(10);
	
	private JLabel ccoaOut22Label = new JLabel("ccoaOut22:");
	private JTextField ccoaOut22Field = new JTextField(10);
	
	private JLabel ccoaOut23Label = new JLabel("ccoaOut23:");
	private JTextField ccoaOut23Field = new JTextField(10);
	
	private JLabel ccoaOut24Label = new JLabel("ccoaOut24:");
	private JTextField ccoaOut24Field = new JTextField(10);
	
	private JLabel ccoaOut25Label = new JLabel("ccoaOut25:");
	private JTextField ccoaOut25Field = new JTextField(10);
	
	private JLabel ccoaOut26Label = new JLabel("ccoaOut26:");
	private JTextField ccoaOut26Field = new JTextField(10);
	
	private JLabel ccoaOut27Label = new JLabel("ccoaOut27:");
	private JTextField ccoaOut27Field = new JTextField(10);
	
	private JLabel ccoaOut28Label = new JLabel("ccoaOut28:");
	private JTextField ccoaOut28Field = new JTextField(10);
	
	private JLabel ccoaOut29Label = new JLabel("ccoaOut29:");
	private JTextField ccoaOut29Field = new JTextField(10);
	
	private JLabel ccoaOut30Label = new JLabel("ccoaOut30:");
	private JTextField ccoaOut30Field = new JTextField(10);
	
	private JLabel ccoaOut31Label = new JLabel("ccoaOut31:");
	private JTextField ccoaOut31Field = new JTextField(10);
	
	private JLabel ccoaOut32Label = new JLabel("ccoaOut32:");
	private JTextField ccoaOut32Field = new JTextField(10);
	
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.getValue("enter.AddCorpNumPanel.tipInfoLabel"));
	private JButton setCallPermissionBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddCorpNumPanel.setCallPermissionBtn"),200,35);
	
	private JButton addCorpNumBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddCorpNumPanel.addCorpNumBtn"),200,35);
	
	private JButton modifyCorpNumBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddCorpNumPanel.modifyCorpNumBtn"),200,35);
	
	private JButton cancleBtn = new MyButton(Properties_language_Utils.
			getValue("enter.AddCorpNumPanel.cancleBtn"));
	
	
	
	Map<RightsEnum,RightsStatusEnum> callRights = new HashMap<>();
	
	
	public AddCorpNumPanel() 
	{
		add(getPanels());
	    init();
	}
	
	private JPanel getPanels() 
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 1, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 1, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {numberLabel}, 2, 1, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {numberField}, 3, 1, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {pwdLabel}, 0, 2, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {pwdField}, 1, 2, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {didNumLabel}, 2, 2, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {didNumField}, 3, 2, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {cxTypeLabel}, 0, 3, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {cxTypeField}, 1, 3, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {statusLabel}, 2, 3, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {statusField}, 3, 3, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {odspModeLabel}, 0, 4, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {odspModeField}, 1, 4, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {outOcxPfxLabel}, 2, 4, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {outOcxPfxField}, 3, 4, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {voipDomainLabel}, 0, 5, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {voipDomainField}, 1, 5, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {sipServerGrpLabel}, 2, 5, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {sipServerGrpField}, 3, 5, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {localGatewayLabel}, 0, 6, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {localGatewayField}, 1, 6, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {localGatewaySyncLabel}, 2, 6, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {localGatewaySyncField}, 3, 6, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {userPriorityLabel}, 0, 7, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {userPriorityField}, 1, 7, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {isConfAccessNumberLabel}, 2, 7, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {isConfAccessNumberField}, 3, 7, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {numberTypeLabel}, 0, 8, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {numberTypeField}, 1, 8, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeLabel}, 2, 8, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {callSrcCodeField}, 3, 8, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeLabel}, 0, 9, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {iupSubscribeField}, 1, 9, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumLabel}, 2, 9, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {rightsEnumField}, 3, 9, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumLabel}, 0, 10, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {rightsStatusEnumField}, 1, 10, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {defaultCallOutLabel}, 2, 10, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {defaultCallOutField}, 3, 10, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {localCallOutLabel}, 0, 11, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {localCallOutField}, 1, 11, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {localTollCallOutLabel}, 2, 11, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {localTollCallOutField}, 3, 11, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {nationalTollCallOutLabel}, 0, 12, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {nationalTollCallOutField}, 1, 12, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {internationalTollCallOutLabel}, 2, 12, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {internationalTollCallOutField}, 3, 12, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {intraLocalTollCallOutLabel}, 0, 13, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {intraLocalTollCallOutField}, 1, 13, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {intraNationalTollCallOutLabel}, 2, 13, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {intraNationalTollCallOutField}, 3, 13, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut1Label}, 0, 14, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut1Field}, 1, 14, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut2Label}, 2, 14, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut2Field}, 3, 14, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut3Label}, 0, 15, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut3Field}, 1, 15, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut4Label}, 2, 15, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut4Field}, 3, 15, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut5Label}, 0, 16, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut5Field}, 1, 16, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut6Label}, 2, 16, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut6Field}, 3, 16, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut7Label}, 0, 17, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut7Field}, 1, 17, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut8Label}, 2, 17, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut8Field}, 3, 17, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut9Label}, 0, 18, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut9Field}, 1, 18, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut10Label}, 2, 18, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut10Field}, 3, 18, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut11Label}, 0, 19, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut11Field}, 1, 19, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut12Label}, 2, 19, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut12Field}, 3, 19, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut13Label}, 0, 20, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut13Field}, 1, 20, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut14Label}, 2, 20, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut14Field}, 3, 20, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut15Label}, 0, 21, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut15Field}, 1, 21, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut16Label}, 2, 21, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut16Field}, 3, 21, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut17Label}, 0, 22, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut17Field}, 1, 22, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut18Label}, 2, 22, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut18Field}, 3, 22, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut19Label}, 0, 23, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut19Field}, 1, 23, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut20Label}, 2, 23, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut20Field}, 3, 23, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut21Label}, 0, 24, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut21Field}, 1, 24, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut22Label}, 2, 24, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut22Field}, 3, 24, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut23Label}, 0, 25, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut23Field}, 1, 25, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut24Label}, 2, 25, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut24Field}, 3, 25, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut25Label}, 0, 26, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut25Field}, 1, 26, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut26Label}, 2, 26, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut26Field}, 3, 26, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut27Label}, 0, 27, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut27Field}, 1, 27, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut28Label}, 2, 27, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut28Field}, 3, 27, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut29Label}, 0, 28, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut29Field}, 1, 28, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut30Label}, 2, 28, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut30Field}, 3, 28, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut31Label}, 0, 29, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut31Field}, 1, 29, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut32Label}, 2, 29, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {ccoaOut32Field}, 3, 29, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {shortNumLabel}, 0, 30, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {shortNumField}, 1, 30, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {setCallPermissionBtn}, 0, 31, 5, 10, 2, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {addCorpNumBtn}, 2, 31, 5, 10, 2, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {modifyCorpNumBtn}, 0, 32, 5, 10, 2, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {cancleBtn}, 2, 32, 5, 10, 1, 1);
        
        buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 33, 5, 10, 1, 1);
        buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 34, 5, 10, 4, 1);
        
        //报文位置
        //message location
        buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
        
        return panel;
	}
	
	private void init() 
	{
		tipInfoLabel.setForeground(Color.blue);
		
		//点击设置权限呼叫按钮事件
		//Click Set Permission Call Button Event
		this.setCallPermissionBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {rightsEnumField, rightsStatusEnumField},
	            		new String[] {"rightsEnum", "rightsStatusEnum"});
	                
	            if (!flag)
	            {
	            	return;
	            }
	            EcService.begin();
	            EcService.loading(errInfoLabel);
	            
	        	Runnable runnable = new Runnable()
				{
            		@Override
            		public void run()
            		{
            			callRights.put(RightsEnum.valueOf(rightsEnumField.getText()), 
            					RightsStatusEnum.valueOf(rightsStatusEnumField.getText()));
            		}
				};
				Future future = Executors.newSingleThreadExecutor().submit(runnable);
				if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//增加企业号码按钮事件
		//Increase company number button event
		this.addCorpNumBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				showErrInfoWithColor("");
				boolean flag = validateParams(
						new JTextField[] {corpIdField, numberField, odspModeField, 
								outOcxPfxField, callSrcCodeField},
						new String[] {"corpId", "number", "odspMode",
								 "outOcxPfx", "callSrcCode"});

				if (!flag) 
				{
					return;
				}
				EcService.begin();
				EcService.loading(errInfoLabel);
				
				Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		addCorpNum();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//修改企业号码按钮事件
		//Modify the company number button event
		this.modifyCorpNumBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				showErrInfoWithColor("");
				boolean flag = validateParams(
						new JTextField[] {corpIdField, numberField},
						new String[] {"corpId", "number"});
	
				if (!flag) 
				{
					return;
				}
				EcService.begin();
				EcService.loading(errInfoLabel);
				
				Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		modifyCorpNum();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(!future.isDone()) {
	            	LOGGER.error("addMouseListener fail");
	            }
			}
		});

	}
	
	//修改企业号码 
	//modify company number
	private void modifyCorpNum() 
	{
		try
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			
			request.setPayload(addCorpNumBean());
			
			EcService.put("/corp/" + corpIdField.getText() + "/number", request, errInfoLabel, token);
			
			EcService.finish();
			
			addCorpNumBean().setPwd(null);
		} 
		catch (Exception e)
		{
			LOGGER.error("get Token error",e);
		}
	}
	
	//增加企业号码 
	//add company number
	private void addCorpNum() 
	{
		try
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_POST);
			
			request.setPayload(addCorpNumBean());
			
			EcService.post("/corp/" + corpIdField.getText() + "/number", request, errInfoLabel, token);
			
			EcService.finish();
			
			addCorpNumBean().setPwd(null);
		} 
		catch (Exception e)
		{
			LOGGER.error("get Token error",e);
		}
	}
	
	//设置增加企业号码请求体参数
	//Set increase company number request body parameters
	private AddCorpNumBean addCorpNumBean() 
	{
		AddCorpNumBean addCorpNumBean = new AddCorpNumBean();
		addCorpNumBean.setNumber(numberField.getText());
		if (StringUtils.isNotEmpty(new String(pwdField.getPassword()))) 
		{
			addCorpNumBean.setPwd(new String(pwdField.getPassword()));
		}
		if (StringUtils.isNotEmpty(didNumField.getText())) 
		{
			addCorpNumBean.setDidNum(didNumField.getText());
		}
		if (StringUtils.isNotEmpty(cxTypeField.getText()))
		{
			addCorpNumBean.setCxType(cxTypeField.getText());
		}
		if (StringUtils.isNotEmpty(statusField.getText()))
		{
			addCorpNumBean.setStatus(statusField.getText());
		}
		addCorpNumBean.setOdspMode(odspModeField.getText());
		addCorpNumBean.setOutOcxPfx(outOcxPfxField.getText());
		if (StringUtils.isNotEmpty(voipDomainField.getText()))
		{
			addCorpNumBean.setVoipDomain(voipDomainField.getText());
		}
		if (StringUtils.isNotEmpty(sipServerGrpField.getText()))
		{
			addCorpNumBean.setSipServerGrp(sipServerGrpField.getText());
		}
		if (StringUtils.isNotEmpty(localGatewayField.getText()))
		{
			addCorpNumBean.setLocalGateway(localGatewayField.getText());
		}
		if (StringUtils.isNotEmpty(localGatewaySyncField.getText()))
		{
			addCorpNumBean.setLocalGatewaySync(Integer.valueOf(localGatewaySyncField.getText()));
		}
		if (StringUtils.isNotEmpty(userPriorityField.getText()))
		{
			addCorpNumBean.setUserPriority(Integer.valueOf(userPriorityField.getText()));
		}
		if (StringUtils.isNotEmpty(isConfAccessNumberField.getText()))
		{
			addCorpNumBean.setIsConfAccessNumber(Integer.valueOf(isConfAccessNumberField.getText()));
		}
		if (StringUtils.isNotEmpty(numberTypeField.getText()))
		{
			addCorpNumBean.setNumberType(Integer.valueOf(numberTypeField.getText()));
		}
		
		addCorpNumBean.setMmTelExtension(addMMTelExtensionBean());
		addCorpNumBean.setCallRights(callRights);
		addCorpNumBean.setCallOutRights(addNumberCallOutRightsVo());
		
		return addCorpNumBean;
	}
	
	//设置用户扩展业务数据
	//Set up user expansion business data
	private MMTelExtensionBean addMMTelExtensionBean() 
	{
		MMTelExtensionBean mMTelExtensionBean = new MMTelExtensionBean();
		mMTelExtensionBean.setCallSrcCode(Integer.parseInt(callSrcCodeField.getText()));
		if (StringUtils.isNotEmpty(iupSubscribeField.getText())) 
		{
			mMTelExtensionBean.setIupSubscribe(Boolean.parseBoolean(iupSubscribeField.getText()));
		}
		
		return mMTelExtensionBean;
	}
	
	//设置前转呼出权限
	//Set forwarding call rights
	private NumberCallOutRightsVo addNumberCallOutRightsVo() 
	{
		NumberCallOutRightsVo numberCallOutRightsVo = new NumberCallOutRightsVo();
		if (StringUtils.isNotEmpty(defaultCallOutField.getText()))
		{
			numberCallOutRightsVo.setDefaultCallOut(Boolean.valueOf(defaultCallOutField.getText()));
		}
		if (StringUtils.isNotEmpty(localCallOutField.getText()))
		{
			numberCallOutRightsVo.setLocalCallOut(Boolean.valueOf(localCallOutField.getText()));
		}
		if (StringUtils.isNotEmpty(localTollCallOutField.getText()))
		{
			numberCallOutRightsVo.setLocalTollCallOut(Boolean.valueOf(localTollCallOutField.getText()));
		}
		if (StringUtils.isNotEmpty(nationalTollCallOutField.getText()))
		{
			numberCallOutRightsVo.setNationalTollCallOut(Boolean.valueOf(nationalTollCallOutField.getText()));
		}
		if (StringUtils.isNotEmpty(internationalTollCallOutField.getText()))
		{
			numberCallOutRightsVo.setInternationalTollCallOut(Boolean.valueOf(internationalTollCallOutField.getText()));
		}
		if (StringUtils.isNotEmpty(intraLocalTollCallOutField.getText()))
		{
			numberCallOutRightsVo.setIntraLocalTollCallOut(Boolean.valueOf(intraLocalTollCallOutField.getText()));
		}
		if (StringUtils.isNotEmpty(intraNationalTollCallOutField.getText()))
		{
			numberCallOutRightsVo.setIntraNationalTollCallOut(Boolean.valueOf(intraNationalTollCallOutField.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut1Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut1(Boolean.valueOf(ccoaOut1Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut2Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut2(Boolean.valueOf(ccoaOut2Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut3Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut3(Boolean.valueOf(ccoaOut3Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut4Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut4(Boolean.valueOf(ccoaOut4Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut5Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut5(Boolean.valueOf(ccoaOut5Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut6Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut6(Boolean.valueOf(ccoaOut6Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut7Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut7(Boolean.valueOf(ccoaOut7Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut8Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut8(Boolean.valueOf(ccoaOut8Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut9Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut9(Boolean.valueOf(ccoaOut9Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut10Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut10(Boolean.valueOf(ccoaOut10Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut11Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut11(Boolean.valueOf(ccoaOut11Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut12Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut12(Boolean.valueOf(ccoaOut12Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut13Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut13(Boolean.valueOf(ccoaOut13Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut14Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut14(Boolean.valueOf(ccoaOut14Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut15Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut15(Boolean.valueOf(ccoaOut15Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut16Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut16(Boolean.valueOf(ccoaOut16Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut17Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut17(Boolean.valueOf(ccoaOut17Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut18Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut18(Boolean.valueOf(ccoaOut18Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut19Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut19(Boolean.valueOf(ccoaOut19Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut20Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut20(Boolean.valueOf(ccoaOut20Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut21Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut21(Boolean.valueOf(ccoaOut21Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut22Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut22(Boolean.valueOf(ccoaOut22Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut23Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut23(Boolean.valueOf(ccoaOut23Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut24Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut24(Boolean.valueOf(ccoaOut24Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut25Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut25(Boolean.valueOf(ccoaOut25Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut26Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut26(Boolean.valueOf(ccoaOut26Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut27Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut27(Boolean.valueOf(ccoaOut27Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut28Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut28(Boolean.valueOf(ccoaOut28Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut29Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut29(Boolean.valueOf(ccoaOut29Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut30Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut30(Boolean.valueOf(ccoaOut30Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut31Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut31(Boolean.valueOf(ccoaOut31Field.getText()));
		}
		if (StringUtils.isNotEmpty(ccoaOut32Field.getText()))
		{
			numberCallOutRightsVo.setCcoaOut32(Boolean.valueOf(ccoaOut32Field.getText()));
		}
		
		return numberCallOutRightsVo;
		
	}
	
	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
	        int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight)
	{
		JPanel subPanel = new JPanel();
    
		for (JComponent component : components)
		{
			component.setFont(new Font("宋体", Font.CENTER_BASELINE, 18));
			subPanel.add(component);
		}
    
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		gridbag.setConstraints(subPanel, c);
		panel.add(subPanel);
	        
	}
	
	private boolean validateParams(JTextField[] textFields, String[] errTexts)
	{
		for (int i = 0; i < textFields.length; i++)
		{
			if (null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText().trim()))
			{
				showErrInfoWithColor(errTexts[i] + Properties_language_Utils.getValue("isNotEmpty"));
				return false;
			}
		}

		return true;
	}
	
	private void showErrInfoWithColor(String errInfo)
	{
		if (null == errInfo)
		{
			return;
		}
        
		errInfoLabel.setForeground(Color.red);
		errInfoLabel.setText(errInfo);
	}

}
