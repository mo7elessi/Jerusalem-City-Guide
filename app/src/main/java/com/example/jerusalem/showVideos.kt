package com.example.jerusalem

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jerusalem.model.VideoModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.fragment_show_vedios.view.*

/**
 * A simple [Fragment] subclass.
 */
class showVideos : Fragment() {

    private var playerView: PlayerView? = null
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playPackPosition: Long = 0

    var videoUrl1 ="https://firebasestorage.googleapis.com/v0/b/firstproject-16ea6.appspot.com/o/vedios%2F%D8%A3%D9%88%D9%84%D9%89%20%D8%A7%D9%84%D9%82%D8%A8%D9%84%D8%AA%D9%8A%D9%86%20%D9%88%D8%AB%D8%A7%D9%84%D8%AB%20%D8%A7%D9%84%D8%AD%D8%B1%D9%85%D9%8A%D9%86..%20%D8%AA%D8%B9%D8%B1%D9%81%20%D8%B9%D9%84%D9%89%20%D9%85%D8%B9%D8%A7%D9%84%D9%85%20%D8%A7%D9%84%D9%85%D8%B3%D8%AC%D8%AF%20%D8%A7%D9%84%D8%A3%D9%82%D8%B5%D9%89%20%D8%A7%D9%84%D9%85%D8%A8%D8%A7%D8%B1%D9%83_360P.mp4?alt=media&token=aa1703aa-6c20-4f59-a81c-515c4d4db134"
    var videoUrl2 ="https://firebasestorage.googleapis.com/v0/b/firstproject-16ea6.appspot.com/o/vedios%2F%D9%85%D8%A7%20%D9%87%D9%8A%20%D8%A7%D9%84%D9%82%D8%AF%D8%B3%20%D9%81%D9%84%D8%B3%D8%B7%D9%8A%D9%86%20%D9%81%D9%8A%20%D8%AF%D9%82%D9%8A%D9%82%D8%A9_360P.mp4?alt=media&token=91d8515b-28b0-416b-a42d-5a62a03141d3"
    var videoUrl3 ="https://firebasestorage.googleapis.com/v0/b/firstproject-16ea6.appspot.com/o/vedios%2F%D8%AC%D9%85%D8%A7%D9%84%20%D9%85%D8%AF%D9%8A%D9%86%D8%A9%20%D8%A7%D9%84%D9%82%D8%AF%D8%B3%F0%9F%8C%B8%20%D8%8C%D8%A3%D8%B1%D9%88%D8%B9%20%D8%A7%D9%84%D8%B9%D8%A8%D8%A7%D8%B1%D8%A7%D8%AA%20%D8%B9%D9%86%D9%87%D8%A7%20!!%F0%9F%92%99%F0%9F%8C%B8%20%D8%A8%D8%A5%D9%86%D8%B4%D8%A7%D8%A6%D9%8A%20%D9%88%D8%A5%D9%84%D9%82%D8%A7%D8%A6%D9%8A%20%F0%9F%8C%B8_360P.mp4?alt=media&token=8840597c-7c31-4d32-921a-14424024cbfd"
    var videoUrl4 ="https://firebasestorage.googleapis.com/v0/b/firstproject-16ea6.appspot.com/o/vedios%2F%D9%85%D8%B9%D8%A7%D9%84%D9%85%20%D8%A7%D9%84%D9%85%D8%B3%D8%AC%D8%AF%20%D8%A7%D9%84%D8%A7%D9%82%D8%B5%D9%8A_360P.mp4?alt=media&token=a06616ad-9f29-43dd-a7d5-14053e182d97"
    var videoUrl5 ="https://firebasestorage.googleapis.com/v0/b/firstproject-16ea6.appspot.com/o/vedios%2F%D9%87%D9%84%20%D8%B4%D8%A7%D9%87%D8%AF%D8%AA%20%D9%85%D9%86%20%D9%82%D8%A8%D9%84%20%D8%AC%D9%85%D8%A7%D9%84%20%D8%A7%D9%84%D9%82%D8%AF%D8%B3%20%D9%84%D9%8A%D9%84%D8%A7%D9%8B%20(%D8%B4%D8%A7%D9%87%D8%AF)_360P.mp4?alt=media&token=892ed234-2f71-41a4-8d35-66255b4f320b"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_show_vedios, container, false)


        val data = mutableListOf<VideoModel>()
        var index = 0

        data.add(VideoModel("تعرف على معالم المسجد الأقصى",videoUrl1))
        data.add(VideoModel("ما هي القدس المحتلة في دقيقة واحدة فقط",videoUrl2))
        data.add(VideoModel("جمال مدينة القدس",videoUrl3))
        data.add(VideoModel("معالم المسجد الأقىى",videoUrl4))
        data.add(VideoModel("هل شاهدت من قبل جمال مدينة القدس ليلا",videoUrl5))

        playerView = root.video

        initVideo(data[index].URL)
        root.titleVideo.text = data[index].title



        root.btnNext.setOnClickListener {

            if (index == data.size-1){
                index = 0
            }

            index++
           player!!.stop()
            initVideo(data[index].URL)
            root.titleVideo.text = data[index].title

        }


        root.btnBack.setOnClickListener {


            if (index ==0){
                index = data.size-1
            }

            index--
            player!!.stop()
            initVideo(data[index].URL)
            root.titleVideo.text = data[index].title

        }


        return root
    }

    private fun initVideo(L: String ) {
        player = ExoPlayerFactory.newSimpleInstance(activity)
        playerView!!.player = player
        val uri = Uri.parse(L)
        val dataSourceFactory = DefaultDataSourceFactory(activity, "exoplayer-codelab")
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

        player!!.playWhenReady = playWhenReady
        player!!.seekTo(currentWindow, playPackPosition)
        player!!.prepare(mediaSource, false, false)

    }



    override fun onPause() {
        super.onPause()
        player!!.stop()
    }

    override fun onStop() {
        super.onStop()
        player!!.stop()
    }
}
