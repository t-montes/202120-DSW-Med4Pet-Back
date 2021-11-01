/*
MIT License
Copyright (c) 2021 Universidad de los Andes - ISIS2603
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.dse.med4pet.exceptions;

public final class ErrorMessage {
	public static final String MASCOTA_NOT_FOUND = "La mascota con el id dado no ha sido encontrada";
	public static final String SERVICIO_NOT_FOUND = "El servicio con el id dado no ha sido encontrado";
	public static final String REGISTROMEDICO_NOT_FOUND = "El registro médico con el id dado no ha sido encontrado";
	public static final String CALIFICACION_NOT_FOUND = "La calificación con el id dado no ha sido encontrada";
	public static final String VETERINARIO_NOT_FOUND = "El veterinario con el id dado no ha sido encontrado";
	public static final String CONTACTO_NOT_FOUND = "El contacto con el id dado no ha sido encontrado";
	
	private ErrorMessage() {
		throw new IllegalStateException("Utility class");
	}
}