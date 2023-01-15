package vendaprodutos.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class Utils {

    private static Validator validador = Validation
            .buildDefaultValidatorFactory()
            .getValidator();
    
    private static DateTimeFormatter dtf = DateTimeFormatter
            .ofPattern( "yyyy-MM-dd" );
    
    public static Long getLong( 
            HttpServletRequest request,
            String nomeParametro ) {
        
        Long v = null;
        
        try {
            v = Long.valueOf( request.getParameter( nomeParametro ) );
        } catch ( NumberFormatException exc ) {
        }
        
        return v;
        
    }

    public static BigDecimal getBigDecimal( 
            HttpServletRequest request,
            String nomeParametro ) {
        
        BigDecimal v = null;
        
        try {
            v = new BigDecimal( request.getParameter( nomeParametro ) );
        } catch ( NumberFormatException exc ) {
        }
        
        return v;
        
    }

    public static Long getLong( String valor ) {
        
        Long v = null;
        
        try {
            v = Long.valueOf( valor );
        } catch ( NumberFormatException exc ) {
        }
        
        return v;
        
    }
    
    public static BigDecimal getBigDecimal( String valor ) {
        
        BigDecimal v = null;
        
        try {
            v = new BigDecimal( valor );
        } catch ( NumberFormatException exc ) {
        }
        
        return v;
        
    }

    public static Date getDate( String data ) {
        
        Date d = null;
        
        try {
            d = Date.valueOf( LocalDate.parse( data, dtf ) );
        } catch ( DateTimeParseException exc ) {
        }
        
        return d;
        
    }

    public static Long getChavePrimariaAposInsercao( 
            PreparedStatement stmt, String nomeColunaChave ) 
            throws SQLException {
        
        Long pk = null;
        
        try ( ResultSet rsPK = stmt.getGeneratedKeys() ) {
            if ( rsPK.next() ) {
                pk = rsPK.getLong( nomeColunaChave );
            }
        }
        
        return pk;
        
    }

    private static Set<ConstraintViolation> validarObj( 
            Object obj,
            String... ignorar ) {

        List<String> ignorarCampos = Arrays.<String>asList( ignorar );
        Set<ConstraintViolation> cvs = new LinkedHashSet<>();
        
        for ( ConstraintViolation cv : validador.validate( obj ) ) {
            if ( !ignorarCampos.contains( 
                    cv.getPropertyPath().toString() ) ) {
                cvs.add( cv );
            }
        }
        
        return cvs;
        
    }

    public static void validar(
            Object obj,
            String... ignorar )
            throws SQLException {
        
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation> cvs = 
                Utils.validarObj( obj, ignorar );
        
        if ( !cvs.isEmpty() ) {
            for ( ConstraintViolation cv : cvs ) {
                sb.append( String.format( 
                        "<li>%s: %s</li>", 
                        cv.getPropertyPath(), 
                        cv.getMessage() ) );
            }

            throw new SQLException( sb.toString() );
            
        }
        
    }
    
    public static RequestDispatcher prepararDespachoErro( 
            HttpServletRequest request,
            String mensagem ) {
        
        request.setAttribute( "mensagemErro", mensagem );
        request.setAttribute( "voltarPara", request.getHeader( "Referer" ) );
        
        return request.getRequestDispatcher( "/erro.jsp" );
        
    }
    
}
