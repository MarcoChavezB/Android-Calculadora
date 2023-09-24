package com.example.practica3_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResultado, textViewPreview, mensajes;
    public Button buttonIgual, info;
    public char operacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.resultado);
        textViewPreview = findViewById(R.id.preview);
        buttonIgual = findViewById(R.id.igual);
        mensajes = findViewById(R.id.mensajes);
        info = findViewById(R.id.info);

        final String[] numeros = new String[2];
        final Boolean[] control = {true};

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        int[] buttonIds = {
                R.id.cero, R.id.uno, R.id.dos, R.id.tres, R.id.cuatro,
                R.id.cinco, R.id.seis, R.id.siete, R.id.ocho, R.id.nueve, R.id.punto,
        };


        int[] operadoresIds = {
                R.id.suma, R.id.resta, R.id.multiplicar, R.id.divicion, R.id.porcentaje,
        };

        int[] clear = {
                R.id.clear, R.id.clearOne,
        };

        for (int buttonId : buttonIds) {
            final Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonText = button.getText().toString();
                    String text = textViewResultado.getText().toString();

                    textViewResultado.setText(text + buttonText);
                }
            });
        }

        for (int operador : operadoresIds) {
            final Button operadores = findViewById(operador);
            operadores.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String operadorText = operadores.getText().toString();
                    String text = textViewResultado.getText().toString();

                    textViewResultado.setText("");
                    numeros[0] = text;
                    textViewPreview.setText(numeros[0]);

                    if (operadorText.length() > 0) {
                        operacion = operadorText.charAt(0);
                    } else {
                        operacion = '\0';
                    }

                }
            });
        }


        for (int clearOperadores : clear) {
            final Button clearOperador = findViewById(clearOperadores);
            clearOperador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (clearOperador.getText().toString().equals("C")) {
                        String text = textViewResultado.getText().toString();

                        if (!text.isEmpty()) {
                            String setText = text.substring(0, text.length() - 1);
                            textViewResultado.setText(setText);
                        }
                    } else if (clearOperador.getText().toString().equals("CE")) {
                        textViewResultado.setText("");
                        textViewPreview.setText("");
                    }
                }
            });
        }


        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeros[1] = textViewResultado.getText().toString();
                switch (operacion) {
                    case '+': {
                        double resultado = Double.parseDouble(numeros[0]) + Double.parseDouble(numeros[1]);
                        textViewResultado.setText(String.valueOf(resultado));
                        textViewPreview.setText("");
                        textViewPreview.setText(numeros[0] + " " + operacion + " " + numeros[1]);

                        break;
                    }
                    case '-': {
                        double resultado = Double.parseDouble(numeros[0]) - Double.parseDouble(numeros[1]);
                        textViewResultado.setText(String.valueOf(resultado));
                        textViewPreview.setText("");
                        textViewPreview.setText(numeros[0] + " " + operacion + " " + numeros[1]);

                        break;
                    }
                    case 'X': {
                        double resultado = Double.parseDouble(numeros[0]) * Double.parseDouble(numeros[1]);
                        textViewResultado.setText(String.valueOf(resultado));
                        textViewPreview.setText("");
                        textViewPreview.setText(numeros[0] + " " + operacion + " " + numeros[1]);

                        break;
                    }
                    case '/': {
                        if (numeros[0].equals("0") || numeros[1].equals("0")) {
                            mensajes.setText("Syntax ERROR!");
                            textViewPreview.setText("");
                            textViewResultado.setText("");

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mensajes.setText("-----");
                                }
                            }, 2000);

                            break;
                        } else {
                            double resultado = Double.parseDouble(numeros[0]) / Double.parseDouble(numeros[1]);
                            textViewResultado.setText(String.valueOf(resultado));
                            textViewPreview.setText("");
                            textViewPreview.setText(numeros[0] + " " + operacion + " " + numeros[1]);

                            break;
                        }

                    }
                    case '%': {
                        double resultado = Double.parseDouble(numeros[0]) % Double.parseDouble(numeros[1]);
                        textViewResultado.setText(String.valueOf(resultado));
                        textViewPreview.setText("");
                        textViewPreview.setText(numeros[0] + " " + operacion + " " + numeros[1]);

                        break;
                    }
                    default: {
                        mensajes.setText("error".toUpperCase());
                        textViewPreview.setText("");
                        textViewResultado.setText("");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mensajes.setText("-----");
                            }
                        }, 2000);
                        break;
                    }

                }

            }
        });
    }

    public void showCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        Button modalCloseButton = dialogView.findViewById(R.id.modalCloseButton);


        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        modalCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



}