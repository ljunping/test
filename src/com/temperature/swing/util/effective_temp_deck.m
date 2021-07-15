% Calculate mean temp.
% sensor nos. 53-56, 57-60, 61-64, 65-68, and 77-80
s=2.29;
A_b=0.7;
A_w=0.95;

T_D1=(T56*s+(T53+T55)*A_w+T54*A_b)/(s+2*A_w+A_b);
T_D2=(T60*s+(T57+T59)*A_w+T58*A_b)/(s+2*A_w+A_b);
T_D3=(T64*s+(T61+T63)*A_w+T62*A_b)/(s+2*A_w+A_b);
T_D4=(T68*s+(T65+T67)*A_w+T66*A_b)/(s+2*A_w+A_b);
T_D5=(T80*s+(T77+T79)*A_w+T78*A_b)/(s+2*A_w+A_b);


% sensor nos. 69-72
s_ct=3.5;
A_b1=2.3;
A_w1=2.3;

T_C1=(T72*s_ct+(T69+T71)*A_w1+T70*A_b1)/(s_ct+2*A_w1+A_b1);

% sensor nos. 73-76
s_cb=4.3;
A_b2=2.4;
A_w2=2.4;

T_C2=(T76*s_cb+(T73+T75)*A_w2+T74*A_b2)/(s_cb+2*A_w2+A_b2);

% Calculate effective temp.
S_chord=0.2;
W_B=2.55;
W_T=4.95;
S_cb=0.2745;
S_ct=0.2670;

T_E=(T_D1*W_T+T_D2*W_B+(T_D3+T_D4)/2*(W_T-S_ct)+T_D5*(W_B-S_cb)+(T_C1+T_C2)*S_chord)/(W_T+W_B+(W_T-S_ct)+(W_B-S_cb)+2*S_chord);


