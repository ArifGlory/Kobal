package putra.kobal;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class InfoJalurActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private PolylineOptions options,option0,option1,option2,option3,option4,option5,option6,option7,option8;
    private PolylineOptions option9,option10,option11,option12,option13,option14,option15;
    Spinner sp_trayek;
    int y,posTerakir;
    private Polyline line;
    private LatLng pointAkhir,titikAwal,titikAkhir;
    public Marker marker_ghost;
    private PopupWindow mpopup;


    String encodeRJB_TKG = "dpw_@i}haS`BwCl@m@z@}@v@k@~AiAnA_ApAaApA}@bA{@bAaAfAaAn@q@h@s@Zo@d@{@p@qAj@gAp@oA`AoBnA_ChA}Bz@oBd@wBTiITiCp@_Cj@mBj@}Bt@wB`AqAhAyAjA_BjAuAt@eApAgBhA}AjAgB`A}Az@}Ar@yAx@uBt@qBp@oBd@{AVs@t@mB~AmDbAiBxAwBnAkAxAq@tBq@jB[bCi@pCe@bDo@xBWlBWlBQxCOzBBjB?tB@`BDvABtABtAB|ADvAB|AH`BH|@FlANxAHnCPnCR`CPvEX~ATfCd@dBTlB^tCn@jB^xAXdBl@dAn@dB|@lB|@lAr@bB~@vAr@lCdAhBj@t@Nf@El@]X[AmB@w@\\QnAFbAN??vANtAN";
    String en_TKG_SKRJ = "j}_@mnmaSQx@Oh@Mj@Qt@J@H@NBj@@L@BBX?r@?z@?A?vAAR@v@?hA@fA@~@?`A@t@@fA@r@@v@BH?T@j@AvA?rAAx@?tBBzAB|A?pA@v@@n@?dB@dB@t@?x@FZEXQXENKNGNBJFDPp@EhAIPANFZE`BOnAOLA`@CrAMjCUvAMlD]z@OXWRSNWJYLs@Rk@JSPUh@g@z@y@PS^k@Z_ADQHURONSTENBVJx@T^N|AAV?`AIbAMt@M\\Id@IlAWpA]bCaAnBw@RIbBe@\\Kh@MTILCdAi@r@c@LK^[b@g@Z[^c@^e@j@m@n@s@d@a@PKdAa@^M`B]h@AtBK\\eAL{@Hk@No@VcAJa@Jo@Fy@@e@DeAD_@De@Hc@DWFYPk@HY\\s@HQ\\s@Xo@h@mAh@_ALW`@i@Xa@LQh@c@^[l@i@RSP[~@oANQdAAzACd@CjA?bAAbBAV?N?HHbAGbAGr@GLu@DYNcAJi@Fq@Bc@?_ACe@Ea@Iu@Ok@S_A]wAUy@c@cAg@cAm@kAi@aAk@iAg@eAw@aBKa@Eg@CkBAgB@_@?c@Dw@Hm@V}AHq@Hc@@_@A_AGe@GiAKsAEs@K_AIs@QwAMaAKq@Im@CO";
    String en_SKRJ_SRGSM = "zef`@etraSOmAOgBK{BIeA?u@LeCHs@L{@VaAXsAVgBTsA\\uBVwAd@oCTcBXiAXkAh@kBf@yANc@p@eBf@sAj@wA`@aAVq@Ti@Xw@Le@Tw@Py@V_AHg@P_BNkANkAVaBHi@RsALw@PaAXyAN]z@}@j@o@~@aAn@o@j@m@hAeAt@s@b@e@bA_Az@{@bAcA`@k@~@cAn@w@j@u@TYHMPU`@g@h@s@b@o@f@u@Zg@LQVg@Ta@Xg@Zo@b@w@b@y@Vg@Ve@Ta@Xg@R]HQV[T]V_@T[X[NQ\\_@LMRQRQNMf@c@\\YhAaAv@q@b@[^[b@[TKTOb@[d@[h@[`Ai@f@]b@Yj@WVMFKb@Oj@Qn@Qt@Sf@Op@YTMh@[XSb@Y~@m@j@c@|AiAhBwAd@]xCuBVQj@g@FEh@m@hAoAPSBATI^_@LIVSXUZWFI`@}@T]j@k@tAcAhAs@rCiB~AaA`@WZS`@[jA{@hAs@VId@Kp@K`AA|@@H?|AZrATlAVxAVfARn@LnARLB~@D\\BR?`@CZGdCk@TI`Ac@z@_@b@Qd@Sl@WVIp@UJG~@Wr@S~@]^S\\Mz@_@HE\\KEW^ULG";
    String en_TKG_GRTG = "j}_@mnmaSMh@Ol@Kd@Sz@CLd@Dl@@PBF@tA?xA?pAAX?R@fA?|@@~@@t@?`@@bA@hA@rA@x@BX@h@Ar@?dBAr@ALoAJwAJiAFkADi@Ds@Bq@Dq@Bo@B_@BcA?kAAcAAInBMt@G`AKl@EbBK\\A?}@?aA@uB?u@?q@@w@@_A?a@@_A@y@?o@?y@@q@@WB_@Fu@?m@?oA?oA?eA?m@?Y@cAMMHa@D_@BQd@LJB`A?xA?z@@pA@bBDT?@w@@q@B_ABqB?m@@i@?m@@KDg@BIHSfAuA^e@X[t@{@b@a@`@c@p@s@p@q@PU`@_@r@m@`@a@`@_@NM`@[hAs@\\SZWPKNGZKVGn@@l@@V?f@?p@?jA?dABxA@R?|AI`BDXAx@?t@AbA?p@AvB?X?l@IVGRGj@W\\Qv@e@t@m@j@g@PO\\g@LU\\_Ah@eBHiAAuAEqC@uADw@Dg@Lk@Po@Ha@Nc@Pc@JQVYPUZ]j@o@RYN]J]Jg@Fq@Ai@C}@Ek@Ce@Cg@AiADkADaADo@B[Fo@Fg@De@Fq@F_ABe@Lg@s@OeAWm@Oe@IE?SFi@SUKSWWQ??@IJm@Lk@Ns@Hg@";
    String en_TKG_WKDS_c = "pt_`@kamaS`@gAiA~C[Ga@Mc@Mc@K[I_@MGTg@MMEWMWG[G[I]GMCUKSGWKWI_@OYKWKi@U]Oa@Uc@YQMWM[O[OSKWOWM[Qe@W]Qs@c@sAw@o@a@g@SWMa@Im@Om@KkAWq@K{@Qy@Og@Kc@Im@Me@Kk@Is@Oy@Mk@Ie@I_@E_@Ec@Eg@Ik@Ao@E]A_@Ce@Cg@E]Ae@G]Cg@Ee@E_@Ci@Gc@Ce@Eq@Ee@Cg@E_@Co@Ca@CaAGiBKsBI}@Ay@Cq@?a@Ao@A_AAcAAc@?uAAa@Ca@Ai@Ac@Ao@?g@A{@B{@Bk@Di@Dk@De@F_@Di@Fc@Fq@Fi@Ho@HACCYEYI[Ga@IYGa@G_@G]E[Ii@Ie@Ea@Io@Gc@EYIw@Ik@Gi@Gm@Ca@Ca@A_@Eg@E_@Ig@Gc@Gk@CSAe@Eg@Ck@CQSQYWWUWUMYOYGUEY?]F[Jm@N{@Jk@Ji@Jk@Lm@He@Hi@Hc@GQMEYG]G[C[?UAYAU?WAUCUCa@COEKMMMSQQQSSOUEKKYEIKESESCSAYC]CUAWCa@AWCQA]A]C]Ae@C[AUC_@EQA]C]CUA[AYAUAU?UC[Aa@Cc@C]Ce@C[A]CYCWA]CQA[A??YC]A]EU?YCYAUAO?SAUASAWCWA]C[C]CWCc@Ea@C[Ei@ESEQGSMIIQQMM[YSSSSOOQQQSQQKKMIWUWSOIWKSCS?[?S@}@@q@@}@Hy@Fs@?e@?a@Eg@Gq@Ig@Go@MYO[Se@[k@[u@Uo@Gk@Em@Ga@Ci@Ma@Es@Eu@GkACaAAw@Aq@Ai@?g@Cc@G[Ec@Ia@Ko@Oe@Qo@UWKm@Mu@Qy@Qc@IMGUOWYM[Q[S[m@_Ae@}@e@_A[o@a@u@Qk@S_AUy@U}@Qy@Og@Yk@a@y@]i@m@_AQYO]Y]_@a@KOWc@S[U]SYOWKMQWOUUYU[Y_@OUU[SY[]OSSSS]W_@O[OYSa@Yc@Yg@S_@Q_@O]KSQa@KWMYMSS[QWQWWYUUSQKWOa@M_@IKMMUW_@?K?[_@e@i@UU[_@e@g@]_@UWUS[a@SUa@i@MQ[]Y]k@m@[]WIc@MMC]Y[WUQYU[Og@W_@QYO[QYQ]WYSMI";
    String en_TKG_WKDS_p = "~u_`@_imaSNBH?TA^BXDRB^DJBDWJ_@Lg@Je@Nk@F_@Fe@Hk@Fa@Fo@Js@Fe@Fa@Fc@@KYEe@Ka@Io@Ma@I_@Ia@Ki@Gi@Gm@Gk@Ii@Cg@DaAFa@@_A@E?CGAQCe@IsAEm@Ce@@_@?WH]H_@He@Ha@L_@L_@Pi@Vs@Pe@L_@Ng@^qAPi@Fc@Ha@He@Fc@Hc@Ji@H_@Hc@Fg@Da@Fa@BO?CWMi@Ya@U]SWMQKWUEEg@J_@JWLSPUTQL[X]R_@V[TUP[^WVU\\Yd@QTUR]Ze@Ze@ZPf@FRARMn@Od@Oj@[jAs@Mi@Ik@IKCKIUQUU[]OG_@K_@Ke@|Bu@dEg@xBcB?g@`@A@IMO[MWIUQ]KYISAGO@QL[Xa@ZONOPOVU^U`@s@c@_Ai@MKSOYO]Q_@Q_@OYKs@Ue@O]Ii@QKC[Ms@Oq@Mi@Mu@S]ISIUMYU]UMKYKe@U_@Oa@Qc@S_@Q_@QYOOGm@Km@Gq@Ec@Aq@Gu@Ao@Ca@Ci@Gi@G{@Is@Aw@?_@BOAWDU?]Ee@Kg@Ai@Cg@E[A]Ck@Ei@E_@E]Aa@Aq@Ae@A]A]AEASQMKYKa@Cg@Ce@IeASs@Mo@KOAq@?m@@k@?o@@e@AI?L]Pg@d@gAf@eAb@w@d@u@b@}@b@_Af@kAj@qA|@gBr@oA^q@j@uAbAuBn@_Ah@q@^e@FU?YGSK_@Oe@O_@I_@C_@Ca@Ce@?c@B]DMBIJKRSX_@V_@N[J[J]LYL]N[L[J[J]Ja@Le@BSBe@H]HQPWFMBW@a@B}@@k@?_@@i@@a@@a@@]?a@?_@@a@@e@?_@@_@@g@?_@@_@@_@?YY?a@Ai@?g@Ae@Eg@EYCm@Cg@CI?_@Ca@CYEWC[G[G]AKBI@o@?i@?g@?e@?q@@q@?s@?o@@y@?a@?cA?c@@m@@_A@o@?o@@[?a@?{@@Y?m@Ao@?o@?u@?mAAQ?S??_@@e@A_@Ee@Ca@G_@I_@IWM_@M]Q_@Oa@O_@i@yAk@{Ak@yA_@cA_@w@a@eAc@gAYw@Mc@I[G]Ge@Gk@Gm@Ea@G{@Ew@Eu@AYi@@m@@c@?a@Ca@C[GWI[I]C_@Ac@Ae@Bw@Bq@@eAAi@?s@@y@@w@?e@Ai@Au@Ay@CeAAeA?u@A{@?}@@iABs@@_@?g@Ce@Ei@E_@C[Aa@Ec@Ci@Eq@Ga@Ee@Im@Kc@Ki@Kg@Eg@Gm@Iy@Ke@G_@E]Ia@Ao@?i@@k@Bs@Bu@Du@Bs@Dm@F[@c@Le@Ju@Nq@L_AJi@Di@@g@?a@?q@Cs@Ga@Cc@Ek@Gc@Ai@?e@Ai@?a@?k@Ck@C[Ia@Kq@OgAEiBNmBd@qBv@eCt@i@X}Ab@qBh@g@?eBE_B@cARg@XkAmAgAwAc@c@g@k@_@a@YGi@OQMYUWSMKWUm@Yc@U[Mm@a@e@Wa@[SM";
    String en_TKG_KMLG = "na``@iylaSYfA{@zCSn@IXERARDFPVZ`@V\\R\\NXFRBN?P?`@AZA^Cb@Cf@Cr@An@C\\?LAJG\\GXI\\GRKd@ETAHA\\CXAT?B@V?\\@\\?^?f@?`@?R@`@B^B^@^B^B^Dl@@l@@j@Bj@@b@Ab@?b@?f@A`@?d@AXAb@AZ?d@?b@?f@?j@@h@?P]Jk@Lq@Ho@JwARe@FW@Q@]AYAU?W@]Bc@BYFWDWJWJUNSLIFONMJQXW^SZOTU`@S`@KRO`@Q^Ud@GP]b@g@n@KNOZUd@OZS^SZW^IJMLOFM?MGQOUSWS_@U_@Ui@Y_@QOCSKc@Uk@YYQg@[g@Ye@UKG_@IYOu@_@m@Yk@[c@U]Sc@[]Wo@[k@Ym@[y@c@c@Ua@Qa@U]Qe@Ws@a@e@Ui@YCRCJGd@G\\G`@Ib@I`@KZOPSR_@^YTUTYXUR_@f@_@f@[d@Y`@W^OXQ`@Mh@I`@Gh@??Ir@E`@I`@E^I^Ib@If@Ib@Kj@Kd@Ib@Od@Mf@Qh@M^]z@Wd@c@r@OPa@h@GJY^c@d@_@\\OP_@d@]j@[f@KVGLETCXCf@A^Af@C^Ad@C\\E`@Mj@Oh@O`@Sb@Ud@c@v@KTOb@GLK^Kf@E^Ix@Ir@O~@Mv@Mh@UdAWpAQt@GRG^Ij@Il@Kt@Kr@OlAIt@At@?PDp@Bn@Bx@@x@?v@Ad@CPI\\GTMVMRM`@Md@CPEZAT@VBVDRJRLPPNb@RZVPPRZ`@`Aj@tA\\t@Pl@^fALb@?L?p@Cd@Gz@Et@Ev@Eh@I|@Gt@Gt@Ej@Er@En@Gv@Mj@Sp@Wv@[|@Wx@GZKn@Eh@Eh@Ef@Ir@ELnBv@`CbArClAtC~AnC`BbGhDnFxCxGpDb@XPT?TG\\Kd@St@W|@Y`ASv@";
    String en_TKG_SRTLG = "fia`@oglaSg@U]Oa@OSG_@QYKo@W]Oa@Oc@Oe@Oq@We@Me@Qo@Sq@Uy@Ys@Uw@Wo@Ue@O}@[}@Ym@Sm@Sy@Yg@O}@Yu@Uo@Oc@KaAUYG_@Ka@KUAa@M_@Ie@Ic@Ky@SaAWaAWo@Ou@SYKQIcASk@Kq@Ws@We@Qe@Q{@_@e@Wc@YYQg@Um@[y@e@a@Se@W]Qi@[k@]m@_@w@c@c@U_@Ky@S{@QcASc@Im@Mm@Kw@Ou@O_@IWGGVGd@In@Kf@If@Ml@Kn@Kd@EVIr@Ih@Kl@Mr@Ij@C\\A\\Ar@AZIZE`@Gz@A|@Ch@CfABXF^J^FRXl@XXFN?JAPE^I`@Kd@Of@KNSLQBWDe@Ba@D_@F]JULUNUVU^MVM\\Sf@Qd@_@r@Wb@[\\]^i@h@g@f@e@d@WTUTc@\\i@b@YVQZIXKf@Kl@G^M`@CDJb@DJFNn@Dj@Rb@Pv@V`@JbAFN?`@Gt@GzAKZFpA_@t@?~An@pDhBJKPW^m@Xc@T[V]f@o@R]NW`@c@h@o@Ze@d@q@^e@HMPOj@c@b@[p@e@XULIh@_@`@Wd@l@d@l@v@dAv@vAZb@rAxAt@`Af@X\\L`@Pz@FfAFlAHx@HjB\\v@LxAZbB^jAVj@V`BjARNv@\\x@T`@BrAL~@LvA\\zBl@n@LZHdAA~@OjB]lBObAElBFrADjBF^@t@@L?d@Ct@CDAf@KCc@Ac@Gi@Gm@C_ACg@Aw@A{@Cs@As@Ak@Ai@Ai@Ai@?e@Ai@Am@Co@@S@_@@QCc@Ca@C_@Ca@?c@Dc@BUDUDUNYFQDG?Ac@O";
    String en_TKG_PRMTBR = "hs_`@u~laSa@Ge@Om@Oe@Me@OGTo@QYMOEcASg@Mo@Us@Ue@Qi@U??e@S_@O_@Ua@WUQq@Yk@[o@]c@Ug@Yg@Wo@a@q@_@c@Yi@[[O]Mm@Og@Ii@Me@KmAU{AYwAWqAYkCe@yB[kBUw@GyBKyBMmD[gCQcCQmDQ_EQoDGcDGo@?m@?{@Ac@Ao@Cg@AeAAy@AcABu@BkAHw@Hi@D{ARq@Fe@F_ALC]Kk@Q{@Q_AO}@M_AIq@M{@MiAMgAOiAIy@Ac@Cs@K{@My@MaANg@Pm@BGRe@Tm@Rg@Pc@Xo@T]Xo@b@o@FKb@}@`@}@^y@Re@Re@LWR]Vk@Xm@JSVc@d@u@Re@Vs@R_@Re@Tc@P[LQHONS\\a@^c@HOFO@QCYMe@[{@GMIk@Ca@Co@Cg@Da@FSJMf@k@V]JONc@Ts@\\y@Zu@Pi@Ng@Li@Dc@@ULe@FMNSFOBY@m@Bq@@m@@i@@m@@o@@c@?q@@k@@e@@q@?q@@e@@g@@m@@_@@_@@g@@_A@_A@kA@a@Bi@Bg@@o@@{@@q@@iA?i@G[KYQ_@g@s@e@s@_@k@MUMQa@m@]e@MS}@uAi@y@q@gAc@m@i@}@Ua@[i@_@m@]m@_@k@m@y@a@k@]i@Yc@KM]u@e@q@_@k@[e@g@}@KOSS]g@Ye@i@w@c@s@Wc@i@s@k@k@g@y@a@o@OWFI?]@g@?y@?q@?SEa@Gc@Ck@?g@Dm@Du@Fu@Bs@@y@?{@@}ABqABeADgA@i@@w@@s@B_A@u@@eABoANoHJwDKaDJ_DDiBVgHH}C?iECcFAu@?kAAoAAc@@u@@q@?[f@?b@?J?Ag@A]b@?";
    String en_TKG_SMPGIRUTM = "l}_`@mnmaSj@sEXgCkEy@i@MsBUaAOy@BsAHeB@QyCKoADs@d@eC`@iAt@yBTq@ZgA^kA\\sBHk@PeAPy@Hu@Ju@BQUM_@Sk@]m@YYQa@_@OKg@m@_@g@Ye@_@w@EOAW@WBWBWJq@@c@@QCs@Co@Go@Eg@M}@Mk@Qs@Km@My@Oy@SgA[aAUg@EKe@aAg@cA[m@i@iAWg@]q@m@eAe@_Ag@cA_@u@[o@e@aAYm@Yk@a@{@Yg@]s@c@w@Se@c@}@]q@c@}@]s@_@w@Qc@We@g@eAq@qA]u@Yg@g@cAm@kA]u@_@q@g@aAi@aAYo@_@y@Um@Yk@Wk@Yq@Wk@O[Sk@Wk@Uk@[u@Wc@{@iA_@m@a@c@e@k@]e@q@}@o@y@U]_@a@m@y@Yg@We@OYqAyAa@c@K[QIg@i@s@u@g@g@]]}@_Ac@c@k@k@e@g@SW]c@a@g@_@k@GSCWAu@Eu@CkA?m@AqA?U@[DMx@kANa@n@qCEIVkAZ_B^q@TmATuAD]Ji@Ha@Lg@Ji@Lg@FWf@wAj@aBNc@Re@JWJ_@Ne@Nk@Po@Ps@Lc@L]J]Po@Tu@Tu@V}@HSJi@FYZe@b@k@JO\\a@b@m@^e@\\c@Vc@\\e@BMFQJgAHc@F_@F]Fk@Lc@H_@H_@Pq@@UDa@@WF[D]He@Js@FYPaAVi@HQHc@Hi@DQb@oADON{@Lm@Ry@Rw@Lk@T_ALk@F[FYHa@Ni@Li@NYZg@\\c@RYV]Zi@Zk@^{@f@sAJ_@B_@Bc@?SAi@@WFQDMPURWf@i@j@q@j@s@RSf@a@TOt@Yn@O|AIdAGhBQlBm@xAOdAIrAYZE~AMt@Mt@Ur@Yb@SfB_AxB_AlAg@hAa@pAa@d@QbAa@`@Gh@Gf@Ob@WTYTe@h@kA^cA`@q@^o@n@y@dAeAdA}@Z]bAsA~@sAx@o@jAgAhBgBd@i@~@gAn@wA^gBVaBNeAd@}Bh@yC^sAN{AJi@CgAFcCDk@p@cDD{@AkB@}BAqCKeAKw@Bm@By@Ro@z@WZMRSTa@Tw@ZkALa@O{@Mg@EOIk@[oAMm@Mi@Oo@M_@e@mAKUSg@Yq@O[Oa@Sc@Yi@_@u@_@w@[q@[k@M[Qe@Wg@m@mA";
    String en_RJB_KMLG_o = "xkw_@afiaS]Te@j@]\\JFf@j@PR\\^z@`@ZNVPTHTJi@bAO\\Oh@S~@Kp@Qz@ETKr@Kl@UtAKh@OhAMn@QdAU~@Yz@c@`AO\\_AzAc@p@i@v@@ZD\\DZ`@Xx@l@j@RXRjA|@hA|@dA|@ZV^Rn@Rv@LZBtADh@@X@RBd@Hb@Hn@Lx@P~Bj@dDz@|Ab@TNXXHRh@rBVjAX`Bd@|B^lBVxAPd@HJNLNNjAj@f@Rv@^p@\\TJh@Tl@VJH`@TPPLRTb@Tp@HRFNNNJHVL`@J|@LPBV?j@ERC^ERG`@Kt@_@j@]NKb@Q`@ONGVET?JANBl@Fd@Hp@JtARbANRBh@@h@AbAId@Gt@ShAYVERE|AOf@Kh@GVCd@D`ALXFbARVHrAb@ZL|@^RHp@`@f@\\VLp@d@lAb@fBVh@FbAR`@J~@Tf@JXLr@VdAn@n@^`@VHFHJLPHt@B`@Bp@B^Hx@Fh@Lt@DPBr@FlA@NARBVFZb@`AP`@VN\\PpDdAGVUz@Mb@C?YK";
    String en_RJB_KMLG_p = "vkw_@_fiaSYP~@k@NCz@r@V@h@@v@CpBGXSt@i@z@m@p@c@\\Ux@u@NM`@Wd@[j@a@v@o@Z]|@y@hAeAz@iAf@cA|@aBh@eAVg@Zk@b@{@h@iAT_@`@w@N]Zi@Te@`@w@P_@Vi@^iAHa@Ho@Fq@@m@B{@BqABs@FkADo@B]DSFYJ_@J[Ng@La@Lg@Pi@HYLi@T{@Ts@Nc@JYLUT[NSX]Za@HKV]n@y@l@w@\\a@l@w@LQRST]f@w@^g@^g@V_@Za@PW^Zv@~@jB|An@t@d@j@FJVvANv@RhA@RD\\JdAJBdAFr@F`@?TAx@Gf@Kt@OB?N`A@TApAN?z@GRAL@LF\\FLBnALH@VFH@~@ExAQr@?~@?B?Ct@Aj@EZ?`ALpAAnACjA@h@Hz@Fb@P`ALr@Lx@PfAHdAFlADnA@h@E`B@jAB\\Rj@Td@|A|BV\\bAxAfAbBtBnCRTPDtCh@b@H`@PNNzAjBl@v@z@hA~A~BHNNj@FdA?|@J`AN`BPhB?xC?r@?zBGbA[~Bc@hBKb@U|@Qt@Qr@GV@XFl@D`@Dj@Hz@FXDFD@H@Z@X?`@?j@AX@f@@bA@VDLBNHO`AQnAMz@Gj@OhAE`@Ap@?XHpADfB?bBAXERKd@OZORUv@IZEb@AT?VDVBNHPNTRPb@R\\VNPR\\^z@f@hAVp@Pd@p@pBFV?r@Cn@Cf@IhAIxAStBEd@Q~CEp@??Ml@Sp@IVSl@e@pAOn@Kr@Gh@KtAM~@O`@g@fASp@[hAUIAC";
    String en_PSRCMG_LMPSG = "lig`@ibmaSZA^CVACq@C}@Cm@CgA?}@A[Bg@@e@?g@Ao@Cs@??????Mo@Qk@?U@c@Ds@Bc@Fo@BY^?t@Br@B\\@t@Dt@B|@DMhAKh@If@MhAM~@MfAMhA?ZBb@@j@FzAP~CDl@DbALx@Fh@DZHh@BTdAVF?HGj@Ux@]x@]NKNKh@]NKj@Yh@Yd@YHE\\Cv@Gr@h@NVfAbAtBpBjAfAXV|@t@\\X^f@HBz@|@dAjAf@h@nBhB\\\\^\\PNx@~@`A~@~@t@`BnAt@p@z@x@l@l@v@t@nAfAn@l@p@p@z@~@t@z@DF~@p@PHNDV@v@DXDLHPTLh@PjA^fCFh@NnAXlCFl@RtANfAR`BRxALbAf@Cl@CjA?p@Bz@D|@DrADZIPGPKXU\\Yv@s@VWTSTKRIb@KXCp@IV@T@XFTFh@TZNt@f@PDR@hA?b@@TANAr@Kp@Ih@Gh@M^Mp@a@fAy@h@i@b@k@H_@Pc@Rg@d@w@\\e@p@}@RWh@i@VY\\a@LKj@_@NKVKb@Qd@Mb@Kl@Ol@Mp@Oj@Ox@S|@Qv@Oz@Oj@Kf@Iz@Oz@Mx@Or@Mp@MbBSr@KzBYrAO~Bc@h@K^MbAWjAYvAUjASjC[pAMTCb@?`ACt@Av@ARA?OBQH]Jk@RaAFa@Ns@Hi@Fc@Fm@BOV?ZB\\F";
    String en_SKRJ_LMPGSG = "xef`@straSJx@Lz@NbAP~ANpAHt@Dr@HbAFbABJD`AAx@Gb@Mv@O|@Kp@En@Cr@Ax@@fA@t@@|@@ZD^FVN\\Tb@Tf@P\\Th@h@`A^p@f@bAb@|@R\\Rd@N^Rp@ZrANn@F\\JZBL??D`@Hn@@V@`@?r@Eh@MdAW`BEXIf@Lp@F\\DPH^Rh@Xx@FPHTZp@Zt@p@bBj@|Af@pAb@hAVr@Zt@\\|@Xj@Tn@b@dAXv@Xh@d@`AFJB@JFDFCPHTRj@Tj@BHXf@d@z@f@jA^`AVj@@FALE`AEz@EfACd@Ir@IbAAHCd@E`ACf@ATr@@`ABr@Bt@Dz@Bv@DKr@Kr@GXKv@Ir@Kv@OnAKx@?\\@X@N?f@Dl@Bl@@VDj@Dt@FdABt@Db@BXF^Jr@?BBTJr@bAVD?JGj@Ur@Y`Aa@PMfAs@r@]\\Sl@_@HAXEv@EB?n@j@NRrBlBdBbBTRZZNPlA`ATP^f@HBn@p@z@~@\\`@~@|@xAtAbA~@z@`Ax@v@b@^v@l@jA|@PLj@j@t@r@h@j@fA`At@p@l@h@d@d@h@d@NPz@bAZ`@pA|@VDj@Bj@DLBFDFFJNNj@Lv@NfAPlALx@Df@LfANvAHr@L`AHj@Jn@LfAVpBPjAf@E`@Al@?f@?l@Bh@Bf@Bh@Bd@@^@N@^KLGPId@a@PK^_@b@_@Z[LKVIJE`@KVC^ETC\\BP@TDTFj@TZPf@ZJFPFR@`@?r@?j@@RE`@EVGv@IXCXGRG\\K^SZU`@YZWh@i@PWPSFWHWPa@FUXe@LUn@u@n@{@l@q@b@e@^e@h@[XQr@Yj@O|A]n@Or@Sr@OnAYt@Ml@Mf@Gv@O`AQd@Gn@Mr@Mt@Mv@Kx@MVCj@K\\Cb@GnAO`AKt@O|@QZIZE\\KVIl@Mf@Md@Mv@K^Ix@Mh@Ir@IhAOf@Ev@InBC~ACT??OBURaANw@TiAF[Js@DYJ{@VAZB\\F";
    String en_TKG_SKRM = "r|_`@snmaSVDR}ANcAHq@Ho@LeABOhAGh@Kd@UXSb@k@r@kAj@cAHOx@f@LNZv@`@jARj@t@O\\IbA]jAg@z@Ud@Gn@GrAQt@MtAKz@K|@ItAI|AM~AM~@IdCM?yA?{@?kA@wA@y@?aB@iA@a@@kB@mA?e@Dg@Fq@?_A?m@?oA?uA?y@@}@AUKKJa@B_@]M]Gc@Cg@Ae@@c@@e@FOBQFQFQNIHQRKRM^KXQLKNo@dAk@bAMLYZ]Xa@Xm@^}@f@q@\\UFg@Fi@Fq@D_A@s@Ew@E_AK_@G{@Is@IeAKs@Ii@GaAMoAMu@G[EY@i@G_AEoAE}BK[C]GsBgAi@YSOUUSOe@k@]e@_@k@[q@EMAW?UFm@Jw@Bq@?o@Ew@Go@Ca@Kw@Kg@S}@Ka@SmAM}@Qy@YaAWm@_@y@Wi@]o@Wk@_@u@c@}@]m@g@cAc@w@Ym@]q@[q@Uc@_@u@Yi@a@{@c@{@[q@i@cAYk@Ug@]s@]q@_@u@a@y@c@}@[u@e@}@_@y@[k@]q@KSKWa@w@k@gAs@uAUc@Sc@Uc@a@q@MWWm@]w@a@{@Ys@Yo@_@y@e@iAa@aA]{@Wg@_@g@g@q@SYKSk@m@W[W][c@a@k@]a@SWOUY[e@m@SYQ]Q[OYEKc@g@[_@c@c@OOK[OIWWa@c@YYYYi@i@UUe@e@q@s@s@u@c@c@o@{@w@eAIOEWAUEgACeAAo@Am@?gA?MB_@DMf@s@NWPa@XqAR}@EIaATy@PQDo@Re@Ns@ZYLiA^yCrAYLwAd@q@Tg@Pa@Pi@Xe@`@s@h@u@j@w@l@w@l@qA~@gAv@w@r@m@f@oA~@aAt@iAv@s@b@WUc@q@U_@Yc@_@k@c@u@KOe@o@k@g@k@_Ak@_ACKMc@c@m@[i@g@u@_@g@a@m@Wg@_@i@e@q@e@q@m@{@W]_@g@c@k@_@o@{@oASWc@w@g@w@e@y@i@y@s@eAaAwAi@u@m@aAg@u@g@w@S]Ya@a@o@i@y@k@y@s@eAk@{@_@k@k@}@c@u@]k@w@oA[c@k@w@k@u@g@w@w@mAe@s@e@u@s@iASUa@o@g@u@o@_Am@{@g@q@s@aAYa@k@_Am@gAi@_AYg@EGCGCEACCAAACe@Em@?Gl@@f@@x@@\\?z@?";

    List<LatLng> listTrayek1 = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_jalur);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.clear();
        LatLng lampung = new LatLng(-5.382351, 105.257791);
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CircleOptions mOptions = new CircleOptions()
                .center(lampung).radius(100)
                .strokeColor(0x110000FF).strokeWidth(8).fillColor(0x110000FF);
        mMap.addCircle(mOptions);

        //  mMap.addMarker(new MarkerOptions().position(lampung).title("lokasi"));

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(lampung));

        Toast.makeText(getApplicationContext(),"Mengambil lokasi..." , Toast.LENGTH_LONG).show();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lampung, 14));
        options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option0 =  new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option1 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option2 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option3 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option4 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option5 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option6 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option7 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option8 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option9 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option10 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option11 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option12 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option13 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option14 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        option15 = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);


        listTrayek1 = decodePoly(encodeRJB_TKG);
        posTerakir = listTrayek1.size();
        titikAwal = listTrayek1.get(0);
        titikAkhir = listTrayek1.get(posTerakir - 1);

        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            options.add(point);
        }
        pointAkhir = listTrayek1.get(posTerakir - 1);
        line = mMap.addPolyline(options);

        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Terminal Rajabasa"));
        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Tanjung Karang"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mpopup.dismiss();
            }
        });

        generatePolylineOptions();

        sp_trayek = findViewById(R.id.sp_trayek1);

        options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);

        sp_trayek.setSelection(0);
        sp_trayek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"diklik",Toast.LENGTH_SHORT).show();
                int pos =  i;

                switch (pos){
                    case 0 :

                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();


                        listTrayek1 = decodePoly(encodeRJB_TKG);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);

                        line = mMap.addPolyline(option0);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Terminal Rajabasa"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Tanjung Karang"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Terminal Rajabasa")){


                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Terimnal rajabasa adalah salah astu terminal di lampung");
                                    imgHeader.setImageResource(R.drawable.rjb);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                   // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah pusat kota bd lampung");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                   // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });
                        break;

                    case 1 :

                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_SKRJ);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option1);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Sukaraja"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang pusat kota");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Sukaraja adalah");
                                    imgHeader.setImageResource(R.drawable.skrj);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 2 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_SKRJ_SRGSM);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option2);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Sukaraja"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Srengsem"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Sukaraja")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Sukaraja adalah");
                                    imgHeader.setImageResource(R.drawable.skrj);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Srengsem adalah");
                                    imgHeader.setImageResource(R.drawable.srengsem);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 3 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_GRTG);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option3);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Garuntang"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("garuntang adalah");
                                    imgHeader.setImageResource(R.drawable.garuntang);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 4 :
                        listTrayek1.clear();
                        mMap.clear();


                        listTrayek1 = decodePoly(en_TKG_WKDS_c);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option4);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Way Kandis (cream)"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Way Kandis adalah");
                                    imgHeader.setImageResource(R.drawable.way_kandis);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 5 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_WKDS_p);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option5);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Way Kandis (pink) "));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Way kandis adalah");
                                    imgHeader.setImageResource(R.drawable.way_kandis);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 6 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_KMLG);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option6);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Kemiling"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Kemiling adalah");
                                    imgHeader.setImageResource(R.drawable.kemiling);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 7 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_SRTLG);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option7);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Sam Ratulangi"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Samratulangi adalah");
                                    imgHeader.setImageResource(R.drawable.samratulangi);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 8 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_PRMTBR);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option8);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Permata Biru"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("PErmata Biru adalah");
                                    imgHeader.setImageResource(R.drawable.permata_biru);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 9 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_SMPGIRUTM);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option9);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung Karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Simpang IR utami"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung Karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Simpang IR utami adalah");
                                    imgHeader.setImageResource(R.drawable.ir_sutami);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 10 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_RJB_KMLG_o);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option10);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Rajabasa"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Kemiling (Kuning Orange)"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Rajabasa")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("rajabasa adalah");
                                    imgHeader.setImageResource(R.drawable.rjb);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Kemiling adalah");
                                    imgHeader.setImageResource(R.drawable.kemiling);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 11 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_RJB_KMLG_p);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option11);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Rajabasa"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Kemiling (Kuning Pekat)"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Rajabasa")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("rajabasa adalah");
                                    imgHeader.setImageResource(R.drawable.rjb);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Kemiling adalah");
                                    imgHeader.setImageResource(R.drawable.kemiling);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });


                        break;

                    case 12 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_TKG_SKRM);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option12);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Tanjung karang"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Sukarame"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Tanjung karang")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Tanjung karang adalah");
                                    imgHeader.setImageResource(R.drawable.tkg);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Sukarame adalah");
                                    imgHeader.setImageResource(R.drawable.sukarame);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 13 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_SKRJ_LMPGSG);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option13);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Sukaraja"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Lempasing"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Sukaraja")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Sukaraja adalah");
                                    imgHeader.setImageResource(R.drawable.skrj);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Lempasing adalah");
                                    imgHeader.setImageResource(R.drawable.lempasing);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;

                    case 14 :
                        listTrayek1.clear();
                        mMap.clear();
                        clearPath();

                        listTrayek1 = decodePoly(en_PSRCMG_LMPSG);
                        posTerakir = listTrayek1.size();
                        titikAwal = listTrayek1.get(0);
                        titikAkhir = listTrayek1.get(posTerakir - 1);

                        pointAkhir = listTrayek1.get(posTerakir - 1);
                        line = mMap.addPolyline(option14);

                        mMap.addMarker(new MarkerOptions().position(titikAwal).title("Pasar Cimeng"));
                        mMap.addMarker(new MarkerOptions().position(titikAkhir).title("Lempasing"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointAkhir, 14));

                        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {
                                if (marker.getTitle().equals("Pasar Cimeng")){

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("pasar cimeng adalah");
                                    imgHeader.setImageResource(R.drawable.pasar_cimeng);


                                    //final AlertDialog alert = alertDialogBuilder.create();
                                    // alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });



                                }else{

                                    marker_ghost = marker;
                                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View v = inflater.inflate(R.layout.dialog_profil_komunitas, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                                    alertDialogBuilder.setView(v);
                                    final TextView txt_namaKomunitas = (TextView) v.findViewById(R.id.txt_dialogNamaKom);
                                    final ImageView imgHeader = v.findViewById(R.id.header_cover_image);
                                    final TextView txt_deskripsi = (TextView) v.findViewById(R.id.txt_dialogTag);
                                    ImageButton btn_keProfilKom = (ImageButton) v.findViewById(R.id.btn_keProfilKom);
                                    final Button btn_close = (Button) v.findViewById(R.id.btn_dialogJoinKom);

                                    txt_namaKomunitas.setText(marker.getTitle());
                                    txt_deskripsi.setText("Lempasing adalah");
                                    imgHeader.setImageResource(R.drawable.lempasing);


                                    // final AlertDialog alert = alertDialogBuilder.create();
                                    //alert.show();

                                    mpopup = new PopupWindow(v,400, 600, true);
                                    mpopup.showAtLocation(v, Gravity.CENTER,5,40);
                                    btn_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mpopup.dismiss();
                                        }
                                    });

                                }
                            }
                        });

                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }

    public void inputTrayekTJK_Rajabasa(){
        listTrayek1.add(new LatLng(-5.368546, 105.236209));
        listTrayek1.add(new LatLng(-5.368808,105.236595));
        listTrayek1.add(new LatLng(-5.370004,105.237818));
        listTrayek1.add(new LatLng(-5.371243,105.238484));
        listTrayek1.add(new LatLng(-5.372034,105.239471));
        listTrayek1.add(new LatLng(-5.372952,105.240458));
        listTrayek1.add(new LatLng(-5.373481,105.241337));
        listTrayek1.add(new LatLng(-5.372952,105.240458));
        listTrayek1.add(new LatLng(-5.374186,105.242453));
        listTrayek1.add(new LatLng(-5.375553,105.244835));
        listTrayek1.add(new LatLng(-5.375767,105.247249));

    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    private void clearPath() {
        if (line != null)
            line.remove();
        line = null;
    }

    private void generatePolylineOptions(){
        //Rajabasa - TKG
        listTrayek1 = decodePoly(encodeRJB_TKG);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option0.add(point);
        }

        //TKG-sukaraja
        listTrayek1 = decodePoly(en_TKG_SKRJ);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option1.add(point);
        }

        //Sukaraja-Srengsem
        listTrayek1 = decodePoly(en_SKRJ_SRGSM);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option2.add(point);
        }

        //TKG-Garuntang
        listTrayek1 = decodePoly(en_TKG_GRTG);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option3.add(point);
        }

        //TKG- waykandis cream
        listTrayek1 = decodePoly(en_TKG_WKDS_c);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option4.add(point);
        }

        //TKG-Waykanids pink
        listTrayek1 = decodePoly(en_TKG_WKDS_p);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option5.add(point);
        }

        //TKG-kemiling
        listTrayek1 = decodePoly(en_TKG_KMLG);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option6.add(point);
        }

        //TKG-Samratulangi
        listTrayek1 = decodePoly(en_TKG_SRTLG);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option7.add(point);
        }

        //TKG-PErmata biru
        listTrayek1 = decodePoly(en_TKG_PRMTBR);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option8.add(point);
        }

        //TKG-SIMpang IR utami
        listTrayek1 = decodePoly(en_TKG_SMPGIRUTM);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option9.add(point);
        }

        //Rajabasa KMLG kuning orang
        listTrayek1 = decodePoly(en_RJB_KMLG_o);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option10.add(point);
        }

        //KMLG kuning pekat
        listTrayek1 = decodePoly(en_RJB_KMLG_p);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option11.add(point);
        }

        //TKG-Sukarame
        listTrayek1= decodePoly(en_TKG_SKRM);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option12.add(point);
        }

        //Sukaraja Lempasing
        listTrayek1 = decodePoly(en_SKRJ_LMPGSG);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option13.add(point);
        }

        //Pasarcimeng- lempasing
        listTrayek1 = decodePoly(en_PSRCMG_LMPSG);
        for (int c=0;c<listTrayek1.size();c++){
            LatLng point = listTrayek1.get(c);
            option14.add(point);
        }

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = sp_trayek.getSelectedItemPosition();

            switch (position){
                case 0 :
                    Toast.makeText(getApplicationContext(),"Ini item ke : "+position,Toast.LENGTH_SHORT).show();
                    break;

                case 1 :
                    Toast.makeText(getApplicationContext(),"Ini item ke : "+position,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


}
