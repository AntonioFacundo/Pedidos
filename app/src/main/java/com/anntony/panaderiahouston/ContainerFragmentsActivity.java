package com.anntony.panaderiahouston;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anntony.panaderiahouston.Adaptador.Adaptador;
import com.anntony.panaderiahouston.Fragments.AgregarFragment;
import com.anntony.panaderiahouston.Fragments.CreadorFragment;
import com.anntony.panaderiahouston.Fragments.HacerPedidoFragment;
import com.anntony.panaderiahouston.Fragments.ListaPedidosFragment;
import com.anntony.panaderiahouston.Fragments.MapaFragment;
import com.anntony.panaderiahouston.Fragments.SobreFragment;
import com.anntony.panaderiahouston.Modelos.Cliente;
import com.anntony.panaderiahouston.Modelos.RellenarDatos;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContainerFragmentsActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        ListaPedidosFragment.OnFragmentInteractionListener,
        HacerPedidoFragment.OnFragmentInteractionListener,
        AgregarFragment.OnFragmentInteractionListener,
        SobreFragment.OnFragmentInteractionListener,
        MapaFragment.OnFragmentInteractionListener,
        CreadorFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragments);
        ButterKnife.bind(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        final View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_referencia);
        CircleImageView imageViewDrawer = headerLayout.findViewById(R.id.imageViewDrawer);
        TextView textViewDrawer = headerLayout.findViewById(R.id.textViewDrawer);

        //Verificar sesion con firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            Cliente cliente = new Cliente();
            cliente.setNombre("Buen día: " + user.getDisplayName());
            cliente.setImageUrl(user.getPhotoUrl().toString());

            Glide.with(navigationView.getContext()).load(cliente.getImageUrl()).into(imageViewDrawer);
            textViewDrawer.setText(cliente.getNombre());
        }else{
            navToLogin();
        }




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea hacer un pedido?")
                .setTitle("Pedido")
                .setCancelable(false)
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Continuar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Fragment fragment = new HacerPedidoFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
                            }
                        });

        //FAB NavDrawer
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //Agregar fragment por defecto
        Fragment fragment = new ListaPedidosFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragment).commit();

        }


    public void salir(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        navToLogin();
    }

    private void navToLogin() {
        Intent intent = new Intent( ContainerFragmentsActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment switchFragment = null;
        boolean flag = false;

        switch (item.getItemId()){
            case R.id.nav_camera:
                switchFragment = new ListaPedidosFragment();
                break;
            case R.id.nav_gallery:
                switchFragment = new HacerPedidoFragment();
                break;
            case R.id.nav_agregar_producto:
                switchFragment = new AgregarFragment();
                break;
            case R.id.nav_share:
                switchFragment = new SobreFragment();
                break;
            case R.id.nav_map:
                switchFragment = new MapaFragment();
                break;
            case R.id.nav_send:
                switchFragment = new CreadorFragment();
                break;
            case R.id.salir:
                salir();
                flag = true;
                break;

        }

        if(!flag)
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, switchFragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
