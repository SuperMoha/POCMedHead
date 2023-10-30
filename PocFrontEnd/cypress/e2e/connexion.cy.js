describe('Connexion', () => {
  it('Visiter la page de connexion et se connecter', () => {
    cy.visit('/');
    cy.contains('h1', 'Connexion');

    cy.fixture('validlogin.json').then((loginData) => {
      cy.intercept('POST', '/patient/login', {
        statusCode: 200,
        body: {
          success: true,
          message: 'Connexion réussie'
        }
      }).as('loginRequest');

      cy.get('#username').type(loginData.username);
      cy.get('#password').type(loginData.password);
      cy.get('[data-cy="submit"]').click();

      localStorage.setItem('lepatient', 1);
      localStorage.setItem('session', 123);

      cy.wait('@loginRequest').then((interception) => {
        expect(interception.response.statusCode).to.eq(200);

      });
    });
  });

  it('Afficher une erreur si les logs sont incorrects', () => {
    cy.visit('/');
    
    cy.fixture('invalidlogin.json').then((loginData) => {
      cy.get('#username').type(loginData.username);
      cy.get('#password').type(loginData.password);
      cy.get('[data-cy=submit]').click();
    });

    cy.on('window:alert', (str) => {
      expect(str).to.equal('Nom dutilisateur ou mot de passe incorrect');
    });
  });

  it('Aller sur la page dinscription', () => {
    cy.visit('/');
    
    cy.get('[data-cy=inscription]').click();
    
    cy.url().should('include', '/Inscription');
  });
});
