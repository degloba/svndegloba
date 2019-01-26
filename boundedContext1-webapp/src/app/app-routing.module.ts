import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
        // { path: 'crisis-center', component: CrisisListComponent },
    // { path: 'hero/:id',      component: HeroDetailComponent },
    // {
    // path: 'heroes',
    /* component: HeroListComponent,
    data: { title: 'Heroes List' }
    }, */
    { path: 'home',
    redirectTo: '/blue',
    pathMatch: 'full'
    },
    // { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
