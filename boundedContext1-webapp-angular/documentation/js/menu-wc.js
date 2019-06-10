'use strict';


customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">bounded-context1-webapp-angular documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                        <li class="link">
                            <a href="dependencies.html" data-type="chapter-link">
                                <span class="icon ion-ios-list"></span>Dependencies
                            </a>
                        </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-toggle="collapse" ${ isNormalMode ?
                                'data-target="#modules-links"' : 'data-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse" ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link">AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' : 'data-target="#xs-components-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' :
                                            'id="xs-components-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DashboardComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">DashboardComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DialogComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">DialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FoodDetailComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">FoodDetailComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FoodListComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">FoodListComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ForgotPasswordComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ForgotPasswordComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HomePageComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">HomePageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LoginPageComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">LoginPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/PolicyListComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">PolicyListComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ProfilePageComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ProfilePageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ProgressPageComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ProgressPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ReportComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ReportComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SignInComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">SignInComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SignUpComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">SignUpComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TodoListComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">TodoListComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TodoListFooterComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">TodoListFooterComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TodoListHeaderComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">TodoListHeaderComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/TodoListItemComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">TodoListItemComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/VerifyEmailComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">VerifyEmailComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#directives-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' : 'data-target="#xs-directives-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' }>
                                        <span class="icon ion-md-code-working"></span>
                                        <span>Directives</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="directives-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' :
                                        'id="xs-directives-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' }>
                                        <li class="link">
                                            <a href="directives/LongPressDirective.html"
                                                data-type="entity-link" data-context="sub-entity" data-context-id="modules">LongPressDirective</a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="chapter inner">
                                    <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                        'data-target="#injectables-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' : 'data-target="#xs-injectables-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' }>
                                        <span class="icon ion-md-arrow-round-down"></span>
                                        <span>Injectables</span>
                                        <span class="icon ion-ios-arrow-down"></span>
                                    </div>
                                    <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' :
                                        'id="xs-injectables-links-module-AppModule-f313407e2c0340a27b32b2e3baa53385"' }>
                                        <li class="link">
                                            <a href="injectables/ActiveStateService.html"
                                                data-type="entity-link" data-context="sub-entity" data-context-id="modules" }>ActiveStateService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/ApiService.html"
                                                data-type="entity-link" data-context="sub-entity" data-context-id="modules" }>ApiService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/AuthService.html"
                                                data-type="entity-link" data-context="sub-entity" data-context-id="modules" }>AuthService</a>
                                        </li>
                                        <li class="link">
                                            <a href="injectables/EventService.html"
                                                data-type="entity-link" data-context="sub-entity" data-context-id="modules" }>EventService</a>
                                        </li>
                                    </ul>
                                </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link">AppRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/ChatModule.html" data-type="entity-link">ChatModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#pipes-links-module-ChatModule-973f0b9819178fbc04ff191c80613401"' : 'data-target="#xs-pipes-links-module-ChatModule-973f0b9819178fbc04ff191c80613401"' }>
                                            <span class="icon ion-md-add"></span>
                                            <span>Pipes</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="pipes-links-module-ChatModule-973f0b9819178fbc04ff191c80613401"' :
                                            'id="xs-pipes-links-module-ChatModule-973f0b9819178fbc04ff191c80613401"' }>
                                            <li class="link">
                                                <a href="pipes/StylizePipe.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">StylizePipe</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#components-links"' :
                            'data-target="#xs-components-links"' }>
                            <span class="icon ion-md-cog"></span>
                            <span>Components</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links"' : 'id="xs-components-links"' }>
                            <li class="link">
                                <a href="components/AppComponent-1.html" data-type="entity-link">AppComponent</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#classes-links"' :
                            'data-target="#xs-classes-links"' }>
                            <span class="icon ion-ios-paper"></span>
                            <span>Classes</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse" ${ isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"' }>
                            <li class="link">
                                <a href="classes/Data.html" data-type="entity-link">Data</a>
                            </li>
                            <li class="link">
                                <a href="classes/DietDays.html" data-type="entity-link">DietDays</a>
                            </li>
                            <li class="link">
                                <a href="classes/Food.html" data-type="entity-link">Food</a>
                            </li>
                            <li class="link">
                                <a href="classes/Goals.html" data-type="entity-link">Goals</a>
                            </li>
                            <li class="link">
                                <a href="classes/Macros.html" data-type="entity-link">Macros</a>
                            </li>
                            <li class="link">
                                <a href="classes/Meal.html" data-type="entity-link">Meal</a>
                            </li>
                            <li class="link">
                                <a href="classes/Persona.html" data-type="entity-link">Persona</a>
                            </li>
                            <li class="link">
                                <a href="classes/Policy.html" data-type="entity-link">Policy</a>
                            </li>
                            <li class="link">
                                <a href="classes/Todo.html" data-type="entity-link">Todo</a>
                            </li>
                            <li class="link">
                                <a href="classes/Units.html" data-type="entity-link">Units</a>
                            </li>
                            <li class="link">
                                <a href="classes/UserData.html" data-type="entity-link">UserData</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#injectables-links"' :
                                'data-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse" ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/ActiveStateService.html" data-type="entity-link">ActiveStateService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/ApiService.html" data-type="entity-link">ApiService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/AuthService.html" data-type="entity-link">AuthService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/EventService.html" data-type="entity-link">EventService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/FireService.html" data-type="entity-link">FireService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/GoogleFitService.html" data-type="entity-link">GoogleFitService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/PolicyService.html" data-type="entity-link">PolicyService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/TodoDataService.html" data-type="entity-link">TodoDataService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#guards-links"' :
                            'data-target="#xs-guards-links"' }>
                            <span class="icon ion-ios-lock"></span>
                            <span>Guards</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse" ${ isNormalMode ? 'id="guards-links"' : 'id="xs-guards-links"' }>
                            <li class="link">
                                <a href="guards/AuthGuard.html" data-type="entity-link">AuthGuard</a>
                            </li>
                            <li class="link">
                                <a href="guards/SecureInnerPagesGuard.html" data-type="entity-link">SecureInnerPagesGuard</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#interfaces-links"' :
                            'data-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse" ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/Sentiment.html" data-type="entity-link">Sentiment</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/User.html" data-type="entity-link">User</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/User-1.html" data-type="entity-link">User</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#miscellaneous-links"'
                            : 'data-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse" ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});