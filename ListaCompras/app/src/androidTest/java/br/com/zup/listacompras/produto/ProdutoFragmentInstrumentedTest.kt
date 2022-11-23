package br.com.zup.listacompras.produto

import android.view.View
import android.widget.EditText
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.zup.listacompras.ERROR_TEXT_DETALHE
import br.com.zup.listacompras.ERROR_TEXT_NOME
import br.com.zup.listacompras.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

//classe de testes de interação com o usuario, testes de ui

@RunWith(AndroidJUnit4::class)
class ProdutoFragmentInstrumentedTest {

    @Test
    fun editTextShowError_whenBothIsEmpty(){
        val scenario = launchFragmentInContainer<ProdutoFragment>()
        onView(withId(R.id.bvAdicionar)).perform(click())
        onView(withId(R.id.etNomeProduto))
            .check(matches(hasErrorText(ERROR_TEXT_NOME)))
        onView(withId(R.id.etDetalheProduto))
            .check(matches(hasErrorText(ERROR_TEXT_DETALHE)))
    }

    @Test
    fun detalheShowErrorAndNomeDoesnt_whenOnlyDetalheIsEmpty(){
        val scenario = launchFragmentInContainer<ProdutoFragment>()
        onView(withId(R.id.etNomeProduto)).perform(typeText("jkgkjgk"))
        onView(withId(R.id.bvAdicionar)).perform(click())
        onView(withId(R.id.etDetalheProduto))
            .check(matches(hasErrorText(ERROR_TEXT_DETALHE)))
        onView(withId(R.id.etNomeProduto))
            .check(matches(hasNoErrorText()))
    }

    @Test
    fun nomeShowErrorAndDetalheDoesnt_whenOnlyNomeIsEmpty(){
        val scenario = launchFragmentInContainer<ProdutoFragment>()
        onView(withId(R.id.etNomeProduto)).perform(typeText(""))
        onView(withId(R.id.bvAdicionar)).perform(click())
        onView(withId(R.id.etDetalheProduto))
            .check(matches(hasNoErrorText()))
        onView(withId(R.id.etNomeProduto))
            .check(matches(hasErrorText(ERROR_TEXT_NOME)))
    }





    fun hasNoErrorText(): BoundedMatcher<View?, EditText> {
        return object : BoundedMatcher<View?, EditText>(EditText::class.java) {

            override fun matchesSafely(view: EditText): Boolean {
                return view.error == null
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("has no error text: ");
            }
        }
    }
}