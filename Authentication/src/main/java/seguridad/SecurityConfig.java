package seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	
	
	
	@Bean
    @Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}





	@Autowired
    private Autenticacion autenticacion;

 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(autenticacion);
    	auth.authenticationProvider(provider);
    }   



    
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and().csrf().disable()
    	
		.authorizeRequests()
		//solo los miembros del rol admin podrán realizar altas
		//y para ver la lista de contactos, tendrán que estar autenticados /guardarAportacion
		
		.antMatchers("/usuario").authenticated()
		.antMatchers("/load").authenticated()
		.antMatchers("/ver-socio").authenticated()
		.antMatchers("/cambiar-contrasena").authenticated()
		.antMatchers("/verAportacion").authenticated()
		.antMatchers("/listar-todos-usuarios").hasAnyAuthority("admin", "ROLE_admin")
		.antMatchers("/guardarAportacion").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/guardar-socio").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/buscar-por-socio").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/borrar-socio").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/guardar-socio").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/guardar-usuario-con-foto").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/extraerFotosUsuario").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/guardarAportacion").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/ver-todas-aportaciones").hasAnyAuthority("admin","ROLE_admin")
		.antMatchers("/buscador-aportaciones").hasAnyAuthority("admin","ROLE_admin")
		.anyRequest().permitAll()
		
		//.antMatchers("/contactos/**").authenticated() .httpBasic();
		.and().addFilter(new JWTAuthenticationFilter(authenticationManager()));
		
    }

}