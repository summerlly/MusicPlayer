package tech.soit.quiet.ui.fragment.base

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import tech.soit.quiet.R
import tech.soit.quiet.model.vo.Music
import tech.soit.quiet.player.core.IMediaPlayer
import tech.soit.quiet.utils.Dummy
import tech.soit.quiet.utils.mock
import tech.soit.quiet.utils.subTitle
import tech.soit.quiet.utils.test.ViewModelUtil
import tech.soit.quiet.utils.testing.SingleFragmentActivity
import tech.soit.quiet.viewmodel.MusicControllerViewModel

@RunWith(AndroidJUnit4::class)
class BottomControllerFragmentTest {


    @get:Rule
    val activity = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    private val bottomController = BottomControllerFragment()

    private val music = Dummy.MUSICS[0]

    private lateinit var viewModel: MusicControllerViewModel

    private val playingMusic = MutableLiveData<Music?>()
    private val playerState = MutableLiveData<Int>()

    @Before
    fun setUp() {
        viewModel = mock()
        Mockito.`when`(viewModel.playerState).thenReturn(playerState)
        Mockito.`when`(viewModel.playingMusic).thenReturn(playingMusic)
        bottomController.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activity.activity.setFragment(bottomController)
    }

    @Test
    fun testBottomControllerShowHide() {
        playingMusic.postValue(null)
        onView(withId(R.id.bottomControllerLayout)).check(matches(not(isDisplayed())))
        onView(withId(R.id.fragmentContentHolder)).check(matches(isCompletelyDisplayed()))

        playingMusic.postValue(music)
        onView(withId(R.id.bottomControllerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.fragmentContentHolder)).check(matches(isDisplayed()))
    }

    @Test
    fun testPlayingState() {
        playingMusic.postValue(music)

        playerState.postValue(IMediaPlayer.PLAYING)
        onView(withId(R.id.controllerPauseOrPlay)).check(matches(withContentDescription(R.string.pause)))

        playerState.postValue(IMediaPlayer.PAUSING)
        onView(withId(R.id.controllerPauseOrPlay)).check(matches(withContentDescription(R.string.play)))

        playerState.postValue(IMediaPlayer.IDLE)
        onView(withId(R.id.controllerPauseOrPlay)).check(matches(withContentDescription(R.string.play)))

        playerState.postValue(IMediaPlayer.PREPARING)
        onView(withId(R.id.controllerPauseOrPlay)).check(matches(withContentDescription(R.string.play)))

    }

    @Test
    fun testMusicContent() {

        playingMusic.postValue(music)
        onView(withId(R.id.musicTitle)).check(matches(withText(music.title)))
        onView(withId(R.id.musicSubTitle)).check(matches(withText(music.subTitle)))
    }

}