package Remontando.util;

public class ValidadorInstrumentos {

    // ==========================
    //   VALIDACIONES CORRIENTE
    // ==========================
    public static void validarCorriente(double monto, String moneda) {

        if (!moneda.equals("CRC")) {
            throw new IllegalArgumentException("Corriente solo se permite en colones.");
        }

        if (monto < 25000) {
            throw new IllegalArgumentException("Monto mínimo para corriente es 25000 colones.");
        }
    }

    public static double tasaCorriente(double monto) {

        if (monto >= 25000 && monto <= 500000) {
            return 0.01;
        } else if (monto <= 1000000) {
            return 0.02;
        } else if (monto <= 2500000) {
            return 0.0225;
        } else if (monto <= 10000000) {
            return 0.025;
        } else {
            return 0.0275;
        }
    }


    // ==========================
    //     VALIDACIONES PACTADA
    // ==========================
    public static void validarPactada(double monto, int dias, String moneda) {

        if (!moneda.equals("CRC") && !moneda.equals("USD")) {
            throw new IllegalArgumentException("Pactada solo en CRC o USD.");
        }

        if (dias < 15) {
            throw new IllegalArgumentException("El plazo mínimo para pactada es 15 días.");
        }

        if (moneda.equals("CRC")) {
            if (monto < 100000) {
                throw new IllegalArgumentException("Monto mínimo pactada CRC es 100000.");
            }
        } else {
            if (monto < 500) {
                throw new IllegalArgumentException("Monto mínimo pactada USD es 500.");
            }
        }
    }

    public static double tasaPactada(int dias, String moneda) {
        boolean crc = moneda.equals("CRC");

        if (dias >= 15 && dias <= 29) {
            if (crc) return 0.0485; else return 0.0080;
        } else if (dias <= 59) {
            if (crc) return 0.0494; else return 0.0091;
        } else if (dias <= 89) {
            if (crc) return 0.0523; else return 0.0106;
        } else if (dias <= 179) {
            if (crc) return 0.0581; else return 0.0144;
        } else if (dias <= 269) {
            if (crc) return 0.0819; else return 0.0221;
        } else if (dias <= 359) {
            if (crc) return 0.0869; else return 0.0226;
        } else if (dias <= 719) {
            if (crc) return 0.0869; else return 0.0240;
        } else if (dias <= 1079) {
            if (crc) return 0.0869; else return 0.0240;
        } else if (dias <= 1439) {
            if (crc) return 0.0869; else return 0.0240;
        } else if (dias <= 1799) {
            if (crc) return 0.0869; else return 0.0240;
        } else {
            if (crc) return 0.0869; else return 0.0240;
        }
    }


    // ==========================
    //   VALIDACIONES CERTIFICADO
    // ==========================
    public static void validarCertificado(double monto, int dias, String moneda) {

        if (!moneda.equals("CRC")) {
            throw new IllegalArgumentException("Certificado solo se permite en CRC.");
        }

        if (dias < 30) {
            throw new IllegalArgumentException("El plazo mínimo para certificado es 30 días.");
        }

        if (dias >= 30 && dias <= 89) {
            if (monto < 100000) {
                throw new IllegalArgumentException("Monto mínimo para certificado entre 30 y 89 días es 100000.");
            }
        } else {
            if (monto < 50000) {
                throw new IllegalArgumentException("Monto mínimo para certificado 90+ días es 50000.");
            }
        }
    }

    public static double tasaCertificado(int dias) {

        if (dias >= 30 && dias <= 59) {
            return 0.0540;
        } else if (dias <= 89) {
            return 0.0570;
        } else if (dias <= 149) {
            return 0.0630;
        } else if (dias <= 179) {
            return 0.0945;
        } else if (dias <= 209) {
            return 0.0995;
        } else if (dias <= 239) {
            return 0.1000;
        } else if (dias <= 359) {
            return 0.0930;
        } else if (dias <= 719) {
            return 0.0930;
        } else if (dias <= 1079) {
            return 0.0930;
        } else if (dias <= 1439) {
            return 0.0930;
        } else if (dias <= 1799) {
            return 0.0930;
        } else {
            return 0.0930;
        }
    }

    public static double retener8(double interes) {
        return interes * 0.92; // 92% queda, 8% se retiene
    }
}
