package com.example.batchmanagingsystem.dto;

import com.example.batchmanagingsystem.error.WrongPartTypeError;

public enum PartName
{
    MIDDLE_MEMBER_U_ENGINE("미들멤버(CRI2-20, U-engine)", "F00V.C02.705", "CRI2-20", 2),
    MIDDLE_MEMBER_R_ENGINE("미들멤버(CRI2-20, R-engine)", "F00V.C02.706", "CRI2-20", 2),
    ARMATURE("아마추어(CRI2-20)", "F00V.C08.520", "CRI2-20", 2),
    VALVE_BODY("밸브피스(CRI2-20)", "F00V.C03.616", "CRI2-20", 3),
    NEEDLE("노즐니들(CRI2-20)", "F00V.W8Z.531", "CRI2-20", 2),
    VALVE_SPOOL("밸브스풀(CRI2-20)", "F00V.C02.600", "CRI2-20", 2),
    VALVE_SPRING("밸브스프링(CRI2 공용)", "F00V.C09.309", "CRI2-20ff", 3),
    VALVE_NUT("밸브너트(CRI2 공용)", "F00V.C13.002", "CRI2-20ff", 2),
    MAGNET_GROUP("터키 마그넷그룹(CRI2-20)", "F00V.C30.409", "CRI2-20", 2),
    MAGNET_CORE("마그넷코어(CRI2-20)", "F00V.C31.502", "CRI2-20", 1),
    NOZZLE_NUT_U_ENGINE("노즐너트(CRI2-20, U-engine)", "F00V.C14.603", "CRI2-20", 2),
    NOZZLE_NUT_R_ENGINE("노즐너트(CRI2-20, R-engine)", "F00V.C14.602", "CRI2-20", 2),
    MIDDLE_MEMBER_A2_ENGINE_CRI2_22AP("미들멤버(CRI2-22AP, A2-engine)", "F00V.C02.721", "CRI2-22AP", 2),
    MIDDLE_MEMBER_R_ENGINE_CRI2_22AP("미들멤버(CRI2-22AP, R-engine)", "F00V.C02.718", "CRI2-22AP", 2),
    NEEDLE_CRI2_22AP("노즐니들(CRI2-22AP)", "F00V.W8Z.535", "CRI2-22AP", 2),
    VALVE_SPOOL_CRI2_22AP("밸브스풀(CRI2-22AP)", "F00V.C02.622", "CRI2-22AP", 2),
    MAGNET_CORE_CRI2_22AP("마그넷코어(CRI2-22AP)", "F00V.C31.504", "CRI2-22AP", 1),
    VALVE_BODY_CRI2_22AP("밸브피스(CRI2-22AP)", "F00V.C03.650", "CRI2-22AP", 3),
    ARMATURE_CRI2_22AP("아마추어(CRI2-22AP)", "F00V.C08.547", "CRI2-22AP", 2);

    final String partName;
    final String partNumber;
    final String partType;
    final int printTimes;

    PartName(String partName, String partNumber, String partType, int printTimes)
    {
        this.partName = partName;
        this.partNumber = partNumber;
        this.partType = partType;
        this.printTimes = printTimes;
    }

    public static PartName checkPartName(String material)
    {
        for(PartName partName : PartName.values())
        {
            if(partName.partNumber.equals(material))
            {
                return partName;
            }
        }
        throw new WrongPartTypeError();
    }
}

//checkPartName에서 부품 번호가 위 enum에서 선언한 부품 번호와 매칭 되지 않을 때, error를 어떻게 처리 할지?
